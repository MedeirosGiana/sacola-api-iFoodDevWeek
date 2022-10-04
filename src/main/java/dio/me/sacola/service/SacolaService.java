package dio.me.sacola.service;

import dio.me.sacola.model.Item;
import dio.me.sacola.model.Sacola;
import dio.me.sacola.resource.dto.ItemDto;

public interface SacolaService {
   Item incluirItemNaSacola(ItemDto itemDto);
   Sacola verSacola(Long id);
   Sacola fecharSacola(Long id, int formaPagamento);
}
