package com.estudo.applicationservice.controller;


//import com.estudo.applicationservice.medico.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

//    @Autowired
//    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody String dados) {
        System.out.println(dados);
    }
}
//
//    @PostMapping
//    @Transactional
//    public void cadastrar(@RequestBody String dados) {
//        repository.save(new Medico(dados));
//    }
//    @GetMapping
//    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
//        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
//    }
//
//    @PutMapping
//    @Transactional
//    public void atualizar(@RequestBody DadosAtualizacaoMedico dados) {
//        var medico = repository.getReferenceById(dados.id());
//        medico.atualizarInformacoes(dados);
//    }
//
//    @DeleteMapping("/{id}")
//    @Transactional
//    public void excluir(@PathVariable Long id) {
//        var medico = repository.getReferenceById(id);
//        medico.excluir();
//    }
//
//
//}
