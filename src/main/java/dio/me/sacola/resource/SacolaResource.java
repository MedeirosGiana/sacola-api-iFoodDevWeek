package dio.me.sacola.resource;

import dio.me.sacola.model.Item;
import dio.me.sacola.model.Sacola;
import dio.me.sacola.resource.dto.ItemDto;
import dio.me.sacola.service.SacolaService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(value="/ifood-devweek/sacola", tags = {"s"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/ifood-devweek/sacola")

public class SacolaResource {
    private final SacolaService sacolaService;

    @PostMapping
    public Item incluirItemNaSacola(@RequestBody ItemDto itemDto) {

        return sacolaService.incluirItemNaSacola(itemDto);
    }

    @GetMapping("/{id}")
    public Sacola verSacola(@PathVariable("id") Long id){
        return sacolaService.verSacola(id);
    }


    @PatchMapping("/fecharSacola/{SacolaId}")
    public Sacola fecharSacola(@PathVariable("SacolaId") Long SacolaId,
                               @RequestParam("formaPagamento") int formaPagamento){
        return sacolaService.fecharSacola(SacolaId, formaPagamento);
    }
}
