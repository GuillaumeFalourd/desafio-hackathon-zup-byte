package com.legacy.controller;

import com.legacy.model.Empresa;
import com.legacy.services.EmpresaService;
import com.legacy.util.ValidadorUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {
    
    private static final Log logger = LogFactory.getLog(EmpresaController.class);
    
    private EmpresaService empresaService;
    
    public void setEmpresaService(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }
    
    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public ModelAndView listarEmpresas(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Listando empresas...");
        
        ModelAndView mv = new ModelAndView("empresa/lista");
        
        try {
            List<Empresa> empresas = empresaService.listarEmpresas();
            mv.addObject("empresas", empresas);
            mv.addObject("total", empresas.size());
        } catch (Exception e) {
            logger.error("Erro ao listar empresas", e);
            mv.addObject("erro", "Erro interno do servidor");
        }
        
        return mv;
    }
    
    @RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
    public String mostrarFormulario(Model model) {
        model.addAttribute("empresa", new Empresa());
        return "empresa/formulario";
    }
    
    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public ModelAndView cadastrarEmpresa(
            @RequestParam("nomeFantasia") String nomeFantasia,
            @RequestParam("razaoSocial") String razaoSocial,
            @RequestParam("cnpj") String cnpj,
            HttpServletRequest request) {
        
        ModelAndView mv = new ModelAndView();
        
        String erro = validarDados(nomeFantasia, razaoSocial, cnpj);
        if (erro != null) {
            mv.setViewName("empresa/formulario");
            mv.addObject("erro", erro);
            mv.addObject("empresa", criarEmpresaComDados(nomeFantasia, razaoSocial, cnpj));
            return mv;
        }
        
        try {
            Empresa empresa = new Empresa(nomeFantasia, razaoSocial, cnpj);
            empresaService.registrarEmpresa(empresa);
            
            mv.setViewName("redirect:/empresa/listar");
            logger.info("Empresa cadastrada com sucesso: " + empresa.getNomeFantasia());
            
        } catch (Exception e) {
            logger.error("Erro ao cadastrar empresa", e);
            mv.setViewName("empresa/formulario");
            mv.addObject("erro", "Erro ao salvar empresa: " + e.getMessage());
            mv.addObject("empresa", criarEmpresaComDados(nomeFantasia, razaoSocial, cnpj));
        }
        
        return mv;
    }
    
    @RequestMapping(value = "/buscar", method = RequestMethod.GET)
    public ModelAndView buscarEmpresa(@RequestParam("id") String idStr) {
        ModelAndView mv = new ModelAndView("empresa/detalhes");
        
        try {
            Long id = Long.parseLong(idStr);
            Empresa empresa = empresaService.buscarEmpresa(id);
            
            if (empresa == null) {
                mv.addObject("erro", "Empresa não encontrada");
            } else {
                mv.addObject("empresa", empresa);
            }
            
        } catch (NumberFormatException e) {
            mv.addObject("erro", "ID inválido");
        } catch (Exception e) {
            logger.error("Erro ao buscar empresa", e);
            mv.addObject("erro", "Erro interno do servidor");
        }
        
        return mv;
    }
    
    private String validarDados(String nomeFantasia, String razaoSocial, String cnpj) {
        if (nomeFantasia == null || nomeFantasia.trim().isEmpty()) {
            return "Nome fantasia é obrigatório";
        }
        if (razaoSocial == null || razaoSocial.trim().isEmpty()) {
            return "Razão social é obrigatória";
        }
        if (!ValidadorUtil.validarCnpj(cnpj)) {
            return "CNPJ inválido";
        }
        return null;
    }
    
    private Empresa criarEmpresaComDados(String nomeFantasia, String razaoSocial, String cnpj) {
        Empresa empresa = new Empresa();
        empresa.setNomeFantasia(nomeFantasia);
        empresa.setRazaoSocial(razaoSocial);
        empresa.setCnpj(cnpj);
        return empresa;
    }
}