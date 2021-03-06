/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.personas.handlers;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import gt.org.ms.api.global.exceptions.ExceptionsManager;
import static gt.org.ms.api.requesting.ValidationsHelper.isNull;
import gt.org.ms.controller.dto.EstudioSaludDto;
import gt.org.ms.controller.dto.IdiomaDto;
import gt.org.ms.controller.dto.PersonaDto;
import gt.org.ms.controller.dto.RegistroLaboralPuestoDto;
import gt.org.ms.controller.dto.RequestUpdatePersonaDto;
import gt.org.ms.converters.DpiDtoConverter;
import gt.org.ms.converters.EstudiosSaludConverter;
import gt.org.ms.converters.IdiomaDtoConverter;
import gt.org.ms.converters.PersonaDtoConverter;
import gt.org.ms.converters.RegistroLaboralPuestosConverter;
import gt.org.ms.model.Dpi;
import gt.org.ms.model.EstudioSalud;
import gt.org.ms.model.HistoricoEstudioSalud;
import gt.org.ms.model.HistoricoIdioma;
import gt.org.ms.model.HistoricoLugarResidencia;
import gt.org.ms.model.HistoricoPersona;
import gt.org.ms.model.HistoricoRegistroAcademico;
import gt.org.ms.model.HistoricoRegistroLaboral;
import gt.org.ms.model.Idioma;
import gt.org.ms.model.LugarResidencia;
import gt.org.ms.model.Persona;
import gt.org.ms.model.Puesto;
import gt.org.ms.model.RegistroAcademico;
import gt.org.ms.model.RegistroLaboral;
import gt.org.ms.model.enums.Estado;
import gt.org.ms.model.enums.EstadoVariable;
import gt.org.ms.model.HistoricoPuesto;
import gt.org.ms.repository.DpiRepository;
import gt.org.ms.repository.EstudiosSaludHistoricoRepository;
import gt.org.ms.repository.EstudiosSaludRepository;
import gt.org.ms.repository.IdiomaHistoricoRepository;
import gt.org.ms.repository.IdiomaRepository;
import gt.org.ms.repository.LugarResidenciaHistoricoRepository;
import gt.org.ms.repository.LugarResidenciaRepository;
import gt.org.ms.repository.PersonasRepository;
import gt.org.ms.repository.PuestoRepository;
import gt.org.ms.repository.RegistroAcademicoRepository;
import gt.org.ms.repository.RegistroLaboralRepository;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import gt.org.ms.repository.PersonasHistoricoRepository;
import gt.org.ms.repository.PuestoHistoricoRepository;
import gt.org.ms.repository.RegistroAcademicoHistoricoRepository;
import gt.org.ms.repository.RegistroLaboralHistoricoRepository;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author edcracken
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PersonaModificarHandler extends PersonasBaseHandler<RequestUpdatePersonaDto, Boolean> {

    @Autowired
    PersonasRepository repo;
    @Autowired
    PersonasHistoricoRepository historicoRepo;
    @Autowired
    IdiomaRepository idiomasRepo;
    @Autowired
    EstudiosSaludRepository estudiosRepo;
    @Autowired
    PersonaDtoConverter converter;
    @Autowired
    RegistroAcademicoRepository rAcaRepository;
    @Autowired
    RegistroLaboralRepository rLabRepository;
    @Autowired
    DpiRepository dpiRepository;
    @Autowired
    LugarResidenciaRepository registroLaboralRepo;
    @Autowired
    RegistroLaboralHistoricoRepository registroLabHistoricoRepo;
    @Autowired
    PuestoRepository puestoRepo;
    @Autowired
    LugarResidenciaHistoricoRepository lugarResidenciaHis;
    @Autowired
    IdiomaHistoricoRepository idiomasHisRepo;
    @Autowired
    RegistroAcademicoHistoricoRepository raHisRepo;
    @Autowired
    EstudiosSaludHistoricoRepository estudiosHisRepo;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Boolean execute(RequestUpdatePersonaDto r) {
        Persona p = repo.findOne(r.getCui());
        actualizarDatosGenerales(p, r)
                .actualizaDpi(p, r)
                .actualizarIdiomas(p, r)
                .actualizaLugarResidencia(p, r)
                .actualizaRegistroAcademico(p, r)
                .actualizarEstudiosSalud(p, r)
                .actualizaRegistroLaboral(p, r);

        return true;
    }

    private void crearHistoricoPersona(Persona persona) {
        HistoricoPersona historicoPersona = new HistoricoPersona();
        BeanUtils.copyProperties(persona, historicoPersona, new String[]{"estado", "id"});
        historicoPersona.setFkPersona(persona);
        historicoPersona.setEstado(Estado.ACTIVO);
        setUpdateInfo(historicoPersona);
        setCreateInfo(historicoPersona);
        historicoRepo.save(historicoPersona);
    }

    private PersonaModificarHandler actualizaLugarResidencia(Persona p, PersonaDto r) {
        LugarResidencia ra;
        crearHistoricoLugarResidencia(ra = p.getLugarResidenciaCollection().iterator().next());
        BeanUtils.copyProperties(r.getLugarResidencia(), ra, new String[]{"id", "fechaCreacion", "creadoPor", "estado"});
        setUpdateInfo(ra);

        registroLaboralRepo.save(ra);
        return this;
    }

    private void crearHistoricoLugarResidencia(LugarResidencia original) {
        HistoricoLugarResidencia historico = new HistoricoLugarResidencia();
        BeanUtils.copyProperties(original, historico, new String[]{"estado"});
        historico.setEstado(Estado.ACTIVO);
        setCreateInfo(historico);

        lugarResidenciaHis.save(historico);
    }

    private PersonaModificarHandler actualizarDatosGenerales(Persona currentPersona, RequestUpdatePersonaDto updateRequest) {
        crearHistoricoPersona(currentPersona);
        BeanUtils.copyProperties(updateRequest, currentPersona);
        currentPersona.setEstado(Estado.ACTIVO);
        if (updateRequest.isLector()) {
            setDatosGeneralesByLector(currentPersona, updateRequest);
        }
        setUpdateInfo(currentPersona);
        repo.save(currentPersona);

        return this;
    }

    private PersonaModificarHandler actualizarEstudiosSalud(Persona p, PersonaDto r) {
        if (!isNull(r.getEstudiosSalud())) {
            if (!Collections2.filter(r.getEstudiosSalud(), new Predicate<EstudioSaludDto>() {
                @Override
                public boolean apply(EstudioSaludDto t) {
                    return isNull(t.getAnioEstudio());
                }
            }).isEmpty()) {
                throw ExceptionsManager.newValidationException("anio_estudio", "Los estudios de salud deben tener el año correspondiente!");
            }
            List<EstudioSalud> estudios;
            crearHistoricoEstudiosSalud((estudios = (List) p.getEstudioSaludCollection()));
            estudiosRepo.deleteInBatch(estudios);
            estudios = new ArrayList<EstudioSalud>();
            for (EstudioSaludDto t : r.getEstudiosSalud()) {
                EstudioSalud i = new EstudiosSaludConverter().toEntity(t);
                i.setFkPersona(p);
                setCreateInfo(i);
                setUpdateInfo(i);
                estudios.add(i);
            }
            estudiosRepo.save(estudios);

        }
        return this;
    }

    private void crearHistoricoEstudiosSalud(List<EstudioSalud> estudios) {
        estudiosHisRepo.save(new ArrayList(Collections2.transform(estudios, new Function<EstudioSalud, HistoricoEstudioSalud>() {
            @Override
            public HistoricoEstudioSalud apply(EstudioSalud f) {
                HistoricoEstudioSalud hEs = new HistoricoEstudioSalud();
                BeanUtils.copyProperties(f, hEs);
                setCreateInfo(hEs);
                return hEs;
            }
        })));
    }

    private PersonaModificarHandler actualizarIdiomas(final Persona p, PersonaDto r) {
        crearHistoricoIdiomas(p.getIdiomaCollection());
        idiomasRepo.deleteByPersona(p.getCui());
        idiomasRepo.save(new ArrayList(Collections2.transform(r.getIdiomas(), new Function<IdiomaDto, Idioma>() {
            @Override
            public Idioma apply(IdiomaDto f) {
                Idioma i = new IdiomaDtoConverter().toEntity(f);
                i.setFkPersona(p);
                setCreateInfo(i);
                return i;
            }
        })));
        return this;
    }

    public void crearHistoricoIdiomas(Collection<Idioma> idiomas) {
        if (!isNull(idiomas)) {
            idiomasHisRepo.save(new ArrayList(Collections2.transform(idiomas,
                    new Function<Idioma, HistoricoIdioma>() {
                @Override
                public HistoricoIdioma apply(Idioma f) {
                    HistoricoIdioma hId = new HistoricoIdioma();
                    BeanUtils.copyProperties(f, hId);
                    setCreateInfo(hId);
                    return hId;
                }
            })));
        }
    }

    private PersonaModificarHandler actualizaRegistroAcademico(Persona p, PersonaDto r) {
        RegistroAcademico ra;
        crearHistoricoRegistroAcademico(ra = p.getRegistroAcademicoCollection().iterator().next());
        BeanUtils.copyProperties(r.getRegistroAcademico(), ra, new String[]{"id", "creadoPor", "fechaCreacion", "estado"});
        setUpdateInfo(ra);
        rAcaRepository.save(ra);

        return this;
    }

    private void crearHistoricoRegistroAcademico(RegistroAcademico ra) {
        HistoricoRegistroAcademico hRa = new HistoricoRegistroAcademico();
        BeanUtils.copyProperties(ra, hRa, new String[]{"estado"});
        setCreateInfo(hRa);
        raHisRepo.save(hRa);
    }

    private PersonaModificarHandler actualizaRegistroLaboral(final Persona persona,
            RequestUpdatePersonaDto requestModPersona) {
        final RegistroLaboral registroLaboral;
        crearHistoricoRegistroLaboral(registroLaboral = persona.getRegistroLaboralCollection().iterator().next());
        BeanUtils.copyProperties(requestModPersona.getRegistroLaboral(), registroLaboral, new String[]{"id", "creadoPor", "fechaCreacion", "estado"});
        setUpdateInfo(registroLaboral);

        puestoRepo.delete((List) registroLaboral.getPuestoCollection());
        registroLaboral.getPuestoCollection().clear();
        rLabRepository.save(registroLaboral);
        puestoRepo.save(new ArrayList(Collections2
                .transform(requestModPersona.getRegistroLaboral().getPuestos(),
                        new Function<RegistroLaboralPuestoDto, Puesto>() {
                    @Override
                    public Puesto apply(RegistroLaboralPuestoDto f) {
                        Puesto puesto = new RegistroLaboralPuestosConverter().toEntity(f);
                        puesto.setFkRegistroLaboral(registroLaboral);
                        setCreateInfo(puesto);
                        return puesto;
                    }
                })));
        return this;
    }

    @Autowired
    PuestoHistoricoRepository puestoHistorico;

    private void crearHistoricoRegistroLaboral(RegistroLaboral actual) {
        HistoricoRegistroLaboral historico = new HistoricoRegistroLaboral();
        BeanUtils.copyProperties(actual, historico);
        setCreateInfo(historico);
        final HistoricoRegistroLaboral hisParent = registroLabHistoricoRepo.save(historico);
        puestoHistorico.save(new ArrayList(Collections2.transform(actual.getPuestoCollection(), new Function<Puesto, HistoricoPuesto>() {
            @Override
            public HistoricoPuesto apply(Puesto f) {
                HistoricoPuesto hp = new HistoricoPuesto();
                BeanUtils.copyProperties(f, hp, new String[]{"fkRegistroLaboral", "id"});
                hp.setFkRegistroLaboral(hisParent);
                setCreateInfo(hp);
                return hp;
            }

        })));
    }

    private PersonaModificarHandler actualizaDpi(Persona p, final RequestUpdatePersonaDto r) {
        Collection<Dpi> ls = Collections2.filter(p.getDpiCollection(), new com.google.common.base.Predicate<Dpi>() {
            @Override
            public boolean apply(Dpi t) {
                return t.getNoSerie().equals(r.getDpi().getNoSerie());
            }
        });
        if (ls.isEmpty()) {
            Dpi ra = new DpiDtoConverter()
                    .toEntity(r.getDpi());
            ra.setEstado(EstadoVariable.ACTUAL);
            ra.setFkPersona(p);
            setCreateInfo(ra);

            dpiRepository.archivarRegitro(p.getCui());
            dpiRepository.save(ra);
        }
        return this;
    }
}
