package com.api.ecommerce.bdd.steps.saleauthorize;


import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import com.api.ecommerce.bdd.steps.AbstractComponentStepsSupport;
import com.api.ecommerce.driver.canal.CanalService;
import com.api.ecommerce.driver.kafka.producer.SaleKafkaProducer;
import com.api.ecommerce.common.DateUtils;
import com.api.ecommerce.controller.ExceptionHandlerController;
import com.api.ecommerce.controller.sale.SaleController;
import com.api.ecommerce.controller.sale.dto.Customer;
import com.api.ecommerce.controller.sale.dto.Item;
import com.api.ecommerce.controller.sale.dto.Order;
import com.api.ecommerce.controller.sale.dto.SaleRequest;
import com.api.ecommerce.controller.sale.dto.SaleResponse;
import com.api.ecommerce.controller.ExceptionResponse;
import com.api.ecommerce.driver.sefaz.SefazService;
import com.api.ecommerce.usecase.saleauthorize.SaleService;
import com.api.ecommerce.driver.tributary.TributaryService;
import com.api.ecommerce.usecase.saleauthorize.repository.SaleRepository;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@WebMvcTest(SaleController.class)
public class SaleAuthorizeComponentSteps extends AbstractComponentStepsSupport {

  @Mock
  private SaleKafkaProducer producer = mock(SaleKafkaProducer.class);

  @Mock
  private SaleRepository saleRepository = mock(SaleRepository.class);

  private final String PATH = "/autorizar-venda";

  private SaleController saleController;

  private MockMvc mockMvc;

  private SaleResponse saleResponse;

  private ExceptionResponse exceptionResponse;

  @Before
  public void init() {
    SefazService sefazService = new SefazService();
    TributaryService tributaryService = new TributaryService();
    CanalService canalService = new CanalService();
    SaleService saleService = new SaleService(producer, tributaryService, sefazService, canalService, saleRepository);
    this.saleController = new SaleController(saleService);

    this.mockMvc = MockMvcBuilders
        .standaloneSetup(saleController)
        .setControllerAdvice(ExceptionHandlerController.class).build();
  }

  @Dado("que o sistema seja executado na seguinte data e hora")
  public void que_o_sistema_seja_executado_na_seguinte_data_e_hora(LocalDateTime date) {
    this.responseDate = date.atZone(ZoneId.of("UTC")).toLocalDateTime();
  }

  @Dado("que seja informado os dados de venda request")
  public void que_seja_informado_os_dados_de_venda_request(SaleRequest sale) {
    this.sale = sale;
  }

  @Dado("que seja informado os dados de ordem pedido")
  public void que_seja_informado_os_dados_de_ordem_pedido(Order order) {
    this.sale.setOrdemPedido(order);
  }

  @Dado("que seja informado os dados de cliente")
  public void que_seja_informado_os_dados_de_cliente(Customer customer) {
    this.sale.setCliente(customer);
  }

  @Dado("que seja informado os dados dos itens")
  public void que_seja_informado_os_dados_dos_itens(List<Item> items) {
    this.sale.setItens(items);
  }

  @Quando("autorizar venda")
  public void autorizar_venda() throws Exception {

    // mock LocalDateTime
    try (MockedStatic<DateUtils> dateMock = mockStatic(DateUtils.class)) {
      dateMock.when(DateUtils::retrieveCurrentDateString).thenReturn(this.responseDate.toString());
      MockHttpServletResponse response = executePost(mockMvc, sale, PATH).getResponse();

      if (isBadRequest(response.getStatus())) {
        this.exceptionResponse = readBodyAsObject(response.getContentAsString(StandardCharsets.UTF_8), ExceptionResponse.class);
      } else {
        this.saleResponse = readBodyAsObject(response.getContentAsString(StandardCharsets.UTF_8), SaleResponse.class);
      }
    }
  }

  @Entao("deveria receber os dados de venda response")
  public void deveria_receber_os_dados_de_venda_response(SaleResponse saleResponse) {
    assertNotNull(this.saleResponse);
    assertEquals(saleResponse.getStatus(), this.saleResponse.getStatus());
    assertEquals(saleResponse.getResponseDate(), this.saleResponse.getResponseDate());
  }

  @Entao("deveria publicar o Json Venda Request na fila {string}")
  public void deveria_publicar_o_json_venda_request_na_fila(String queue) {
    Mockito.verify(this.producer, times(1)).execute(Mockito.any(SaleRequest.class));
  }

  @Entao("deveria retornar as seguintes mensagens")
  public void deveria_retornar_as_seguintes_mensagens(ExceptionResponse exceptionResponse) {
    assertEquals(exceptionResponse.getMessage(), this.exceptionResponse.getMessage());
  }

  @Entao("nao deveria publicar nenhum JSON na fila")
  public void nao_deveria_publicar_nenhum_json_na_fila(io.cucumber.datatable.DataTable dataTable) {
    Mockito.verify(this.producer, never()).execute(this.sale);
  }
}
