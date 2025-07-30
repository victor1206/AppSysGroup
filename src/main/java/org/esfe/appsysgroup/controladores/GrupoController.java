package org.esfe.appsysgroup.controladores;

import org.esfe.appsysgroup.modelos.Grupo;
import org.esfe.appsysgroup.servicios.interfaces.IGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/grupos")
public class GrupoController {
    @Autowired
    private IGrupoService grupoService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size)
    {
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable paggeable = PageRequest.of(currentPage, pageSize);

        Page<Grupo> grupos = grupoService.buscarTodosPaginados(paggeable);
        model.addAttribute("grupos", grupos);

        int totalPage = grupos.getTotalPages();
        if(totalPage > 0)
        {
            List<Integer> pageNumber = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumber);
        }

        return "grupo/index";
    }

    @GetMapping("/create")
    public String create(Grupo pGrupo)
    {
        return "grupo/create";
    }

    @PostMapping("/save")
    public String save(Grupo pGrupo, BindingResult result, Model model, RedirectAttributes attributes)
    {
        if(result.hasErrors())
        {
            model.addAttribute(pGrupo);
            return "grupo/create";
        }
        grupoService.createOrEdit(pGrupo);
        attributes.addFlashAttribute("msg", "Grupo se Guardo exitosamente");
        return "redirect:/grupos";
    }
}
