package dio.me.sacola.model;

import ch.qos.logback.core.net.server.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dio.me.sacola.enumeration.FormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Enumeration;
import java.util.List;
@AllArgsConstructor
@Builder
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor

 public class Sacola {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JsonIgnore
  private Cliente cliente;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Item> itens;
  private double valorTotal;

  @Enumerated
  private FormaPagamento formaPagamento;
  private boolean fechada;

 }

