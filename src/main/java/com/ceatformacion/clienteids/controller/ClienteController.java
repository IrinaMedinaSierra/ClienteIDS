package com.ceatformacion.clienteids.controller;


import com.ceatformacion.clienteids.model.Clientes;
import com.ceatformacion.clienteids.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClienteController {

    //creamos una llamada @Autowired  que permite que inyectar las dependencias del hibernate con la clase
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model){
        model.addAttribute("cliente",new Clientes());
        return "formulario";
    }

    @PostMapping("/crud")
    public String guardarCliente(@ModelAttribute Clientes cliente){
        clienteRepository.save(cliente);
        return "redirect:/crud";
    }

    @GetMapping("/crud")
    public String mostrarCRUD(Model model){
        model.addAttribute("clientes",clienteRepository.findAll());
        return "crud";
    }
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model){
        //buscar el bbdd ese id para poder editarlo
        Clientes cliente = clienteRepository.findById(id).get();
        model.addAttribute("cliente",cliente);
        return "formulario";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id){
        clienteRepository.deleteById(id);
        return "redirect:/crud";
    }
}
