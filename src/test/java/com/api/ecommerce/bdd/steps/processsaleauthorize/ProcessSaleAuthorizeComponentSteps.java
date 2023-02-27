package com.api.ecommerce.bdd.steps.processsaleauthorize;

import java.util.List;

import com.api.ecommerce.bdd.steps.AbstractComponentStepsSupport;
import com.api.ecommerce.controller.sale.dto.SaleRequest;
import com.api.ecommerce.driver.canal.CallbackResponse;
import com.api.ecommerce.driver.kafka.consumer.SaleKafkaConsumer;
import com.api.ecommerce.driver.sefaz.AuthorizationResponse;
import com.api.ecommerce.driver.tributary.Tribute;
import com.api.ecommerce.entity.SaleTable;
import com.api.ecommerce.usecase.saleauthorize.repository.SaleRepository;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.test.context.junit4.SpringRunner;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessSaleAuthorizeComponentSteps extends AbstractComponentStepsSupport {

  @Autowired
  private SaleRepository saleRepository;

  @Autowired
  private SaleKafkaConsumer saleKafkaConsumer;

  private SaleRequest saleRequest;

  @Mock
  private Acknowledgment ackMock = mock(Acknowledgment.class);

  @Dado("que existam os seguintes tributo response cadastrados externamente")
  public void que_existam_os_seguintes_tributo_response_cadastrados_externamente(List<Tribute> tribute) {

  }

  @Dado("que existam os seguintes authorize response cadastrados externamente")
  public void que_existam_os_seguintes_authorize_response_cadastrados_externamente(AuthorizationResponse authorizationResponse) {
  }

  @Dado("que existam os seguintes canal response cadastrados externamente")
  public void que_existam_os_seguintes_canal_response_cadastrados_externamente(CallbackResponse callbackResponse) {

  }

  @Dado("que existam as vendas cadastradas")
  public void que_existam_as_vendas_cadastradas(SaleTable saleTable) {
    saleRequest = saleTable.getSaleRequest();
    saleRepository.save(saleTable);
  }

  @Quando("processar autorizacao venda")
  public void processar_autorizacao_venda() {
    saleKafkaConsumer.consume(new ConsumerRecord<>("autorizar-venda-queue", 1, 1, "autorizar-venda-queue-v1", saleRequest), ackMock);
  }

  @Entao("deveria existir as seguintes vendas na base")
  public void deveria_existir_as_seguintes_vendas_na_base(io.cucumber.datatable.DataTable dataTable) {

  }

  @Entao("deveria enviar para o endpoint authorize as informacoes de request esperadas")
  public void deveria_enviar_para_o_endpoint_authorize_as_informacoes_de_request_esperadas(io.cucumber.datatable.DataTable dataTable) {

  }

  @Entao("deveria enviar para o endpoint authorize as informacoes de customer esperadas")
  public void deveria_enviar_para_o_endpoint_authorize_as_informacoes_de_customer_esperadas(io.cucumber.datatable.DataTable dataTable) {

  }

  @Entao("deveria enviar para o endpoint authorize as informacoes de product esperadas")
  public void deveria_enviar_para_o_endpoint_authorize_as_informacoes_de_product_esperadas(io.cucumber.datatable.DataTable dataTable) {

  }

  @Entao("deveria enviar para o endpoint callback-venda as informacoes de request esperadas")
  public void deveria_enviar_para_o_endpoint_callback_venda_as_informacoes_de_request_esperadas(io.cucumber.datatable.DataTable dataTable) {

  }

}
