## language: pt
#
#Funcionalidade: Autorizar Venda
#
#  Cenario de Fundo:
#    Dado que o sistema seja executado na seguinte data e hora
#      | Ano  | Mes | Dia | Hora | Minuto | Segundo |
#      | 2022 | 11  | 11  | 15   | 47     | 10      |
#
#  Cenario: 01 - Realizar autorizacao venda com todos os dados validos - Sucesso
#    Dado que seja informado os dados de venda request
#      | Canal | Empresa | Loja | Pdv | Total Itens | Quantidade Itens |
#      | APP   | 00001   | 0001 | 501 | 38744       | 6                |
#    E que seja informado os dados de ordem pedido
#      | Numero Pedido | Numero Ordem Externo | Data Autorizacao        |
#      | 200010710363  | 2312529489023-1      | 2022-11-11T15:37:56.194 |
#    E que seja informado os dados de cliente
#      | Id     | Nome                       | Documento   | Tipo Documento | Tipo Pessoa | Endereco                  | Numero Endereco | Complemento Endereco | Bairro            | Cidade | Estado | Pais | Cep      | Codigo Ibge | Telefone        | Email                                |
#      | 123456 | Givaldo Santos Vasconcelos | 70420816097 | CPF            | F           | Travessa Francisco Vieira | 11              | Apto 405             | Trapiche da Barra | Maceió | AL     | BR   | 57010460 | 7162435     | (82) 36774-7713 | givaldo.santos.vasconcelos@gmail.com |
#    E que seja informado os dados dos itens
#      | Sku       | Quantidade | Valor |
#      | 547170100 | 3          | 5691  |
#      | 557882194 | 2          | 7990  |
#      | 557282711 | 1          | 5691  |
#    Quando autorizar venda
#    Entao deveria receber os dados de venda response
#      | Status           | Data Resposta       |
#      | EM_PROCESSAMENTO | 2022-11-11T15:47:10 |
#    E deveria publicar o Json Venda Request na fila "autorizar-venda-queue"
#
#  Cenario: 02 - Realizar autorizacao venda com todos os dados nao informados - Fluxo excepcional
#    Dado que seja informado os dados de venda request
#      | Canal | Empresa | Loja | Pdv | Total Itens | Quantidade Itens |
#      |       |         |      |     |             |                  |
#    Quando autorizar venda
#    Entao deveria retornar as seguintes mensagens
#      | Http Status | Message                                                                                                                                                                                                                                                                                                                                                      |
#      | BAD_REQUEST | canal não deve estar em branco, canal não deve ser nulo, cliente não deve ser nulo, empresa não deve estar em branco, empresa não deve ser nulo, itens não deve estar vazio, itens não deve ser nulo, loja não deve estar em branco, loja não deve ser nulo, ordemPedido não deve ser nulo, pdv não deve ser nulo, quantidadeItens não deve ser nulo, totalItens não deve ser nulo. |
#    E nao deveria publicar nenhum JSON na fila
#      | Nome Fila             |
#      | autorizar-venda-queue |
#
#  Cenario: 03 - Realizar autorizacao venda com todos os dados de ordem pedido nao informados - Fluxo excepcional
#    Dado que seja informado os dados de venda request
#      | Canal | Empresa | Loja | Pdv | Total Itens | Quantidade Itens |
#      | APP   | 00001   | 0001 | 501 | 38744       | 6                |
#    E que seja informado os dados de ordem pedido
#      | Numero Pedido | Numero Ordem Externo | Data Autorizacao |
#      |               |                      |                  |
#    E que seja informado os dados de cliente
#      | Id     | Nome                       | Documento   | Tipo Documento | Tipo Pessoa | Endereco                  | Numero Endereco | Complemento Endereco | Bairro            | Cidade | Estado | Pais | Cep      | Codigo Ibge | Telefone        | Email                                |
#      | 123456 | Givaldo Santos Vasconcelos | 70420816097 | CPF            | F           | Travessa Francisco Vieira | 11              | Apto 405             | Trapiche da Barra | Maceió | AL     | BR   | 57010460 | 7162435     | (82) 36774-7713 | givaldo.santos.vasconcelos@gmail.com |
#    E que seja informado os dados dos itens
#      | Sku       | Quantidade | Valor |
#      | 547170100 | 3          | 5691  |
#      | 557882194 | 2          | 7990  |
#      | 557282711 | 1          | 5691  |
#    Quando autorizar venda
#    Entao deveria retornar as seguintes mensagens
#      | Http Status | Message                                                                                                                                                                                                                                                  |
#      | BAD_REQUEST | ordemPedido.dataAutorizacao não deve ser nulo, ordemPedido.numeroOrdemExterno não deve estar em branco, ordemPedido.numeroOrdemExterno não deve ser nulo, ordemPedido.numeroPedido não deve estar em branco, ordemPedido.numeroPedido não deve ser nulo. |
#    E nao deveria publicar nenhum JSON na fila
#      | Nome Fila             |
#      | autorizar-venda-queue |
#
#  Cenario: 04 - Realizar autorizacao venda com todos os dados de cliente nao informados - Fluxo excepcional
#    Dado que seja informado os dados de venda request
#      | Canal | Empresa | Loja | Pdv | Total Itens | Quantidade Itens |
#      | APP   | 00001   | 0001 | 501 | 38744       | 6                |
#    E que seja informado os dados de ordem pedido
#      | Numero Pedido | Numero Ordem Externo | Data Autorizacao        |
#      | 200010710363  | 2312529489023-1      | 2022-11-11T15:37:56.194 |
#    E que seja informado os dados de cliente
#      | Id | Nome | Documento | Tipo Documento | Tipo Pessoa | Endereco | Numero Endereco | Complemento Endereco | Bairro | Cidade | Estado | Pais | Cep | Codigo Ibge | Telefone | Email |
#      |    |      |           |                |             |          |                 |                      |        |        |        |      |     |             |          |       |
#    E que seja informado os dados dos itens
#      | Sku       | Quantidade | Valor |
#      | 547170100 | 3          | 5691  |
#      | 557882194 | 2          | 7990  |
#      | 557282711 | 1          | 5691  |
#    Quando autorizar venda
#    Entao deveria retornar as seguintes mensagens
#      | Http Status | Message                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
#      | BAD_REQUEST | cliente.bairro não deve estar em branco, cliente.bairro não deve ser nulo, cliente.cep não deve estar em branco, cliente.cep não deve ser nulo, cliente.cidade não deve estar em branco, cliente.cidade não deve ser nulo, cliente.codigoIbge não deve estar em branco, cliente.codigoIbge não deve ser nulo, cliente.documento não deve estar em branco, cliente.documento não deve ser nulo, cliente.email não deve estar em branco, cliente.email não deve ser nulo, cliente.endereco não deve estar em branco, cliente.endereco não deve ser nulo, cliente.estado não deve estar em branco, cliente.estado não deve ser nulo, cliente.id não deve estar em branco, cliente.id não deve ser nulo, cliente.nome não deve estar em branco, cliente.nome não deve ser nulo, cliente.numeroEndereco não deve estar em branco, cliente.numeroEndereco não deve ser nulo, cliente.pais não deve estar em branco, cliente.pais não deve ser nulo, cliente.telefone não deve estar em branco, cliente.telefone não deve ser nulo, cliente.tipoDocumento não deve estar em branco, cliente.tipoDocumento não deve ser nulo, cliente.tipoPessoa não deve ser nulo. |
#    E nao deveria publicar nenhum JSON na fila
#      | Nome Fila             |
#      | autorizar-venda-queue |
#
#  Cenario: 05 - Realizar autorizacao venda com todos os dados de item nao informados - Fluxo excepcional
#    Dado que seja informado os dados de venda request
#      | Canal | Empresa | Loja | Pdv | Total Itens | Quantidade Itens |
#      | APP   | 00001   | 0001 | 501 | 38744       | 6                |
#    E que seja informado os dados de ordem pedido
#      | Numero Pedido | Numero Ordem Externo | Data Autorizacao        |
#      | 200010710363  | 2312529489023-1      | 2022-11-11T15:37:56.194 |
#    E que seja informado os dados de cliente
#      | Id     | Nome                       | Documento   | Tipo Documento | Tipo Pessoa | Endereco                  | Numero Endereco | Complemento Endereco | Bairro            | Cidade | Estado | Pais | Cep      | Codigo Ibge | Telefone        | Email                                |
#      | 123456 | Givaldo Santos Vasconcelos | 70420816097 | CPF            | F           | Travessa Francisco Vieira | 11              | Apto 405             | Trapiche da Barra | Maceió | AL     | BR   | 57010460 | 7162435     | (82) 36774-7713 | givaldo.santos.vasconcelos@gmail.com |
#    E que seja informado os dados dos itens
#      | Sku | Quantidade | Valor |
#      |     |            |       |
#    Quando autorizar venda
#    Entao deveria retornar as seguintes mensagens
#      | Http Status | Message                                                                                                  |
#      | BAD_REQUEST | itens[0].quantidade não deve ser nulo, itens[0].sku não deve ser nulo, itens[0].valor não deve ser nulo. |
#    E nao deveria publicar nenhum JSON na fila
#      | Nome Fila             |
#      | autorizar-venda-queue |
#
