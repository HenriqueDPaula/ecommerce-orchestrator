# language: pt

Funcionalidade: Processar Autorizacao Venda

  Cenario de Fundo:
    Dado que o sistema seja executado na seguinte data e hora
      | Ano  | Mes | Dia | Hora | Minuto | Segundo |
      | 2022 | 11  | 11  | 15   | 47     | 10      |
    E que seja informado os dados de venda request
      | Canal | Empresa | Loja | Pdv | Total Itens | Quantidade Itens |
      | APP   | 00001   | 0001 | 501 | 38744       | 6                |
    E que seja informado os dados de ordem pedido
      | Numero Pedido | Numero Ordem Externo | Data Autorizacao        |
      | 200010710363  | 2312529489023-1      | 2022-11-11T15:37:56.194 |
    E que seja informado os dados de cliente
      | Id     | Nome                       | Documento   | Tipo Documento | Tipo Pessoa | Endereco                  | Numero Endereco | Complemento Endereco | Bairro            | Cidade | Estado | Pais | Cep      | Codigo Ibge | Telefone        | Email                                |
      | 123456 | Givaldo Santos Vasconcelos | 704208160 97 | CPF            | F           | Travessa Francisco Vieira | 11              | Apto 405             | Trapiche da Barra | Maceió | AL     | BR   | 57010460 | 7162435     | (82) 36774-7713 | givaldo.santos.vasconcelos@gmail.com |
    E que seja informado os dados dos itens
      | Sku       | Quantidade | Valor |
      | 547170100 | 3          | 5691  |
      | 557882194 | 2          | 7990  |
      | 557282711 | 1          | 5691  |
    E que existam os seguintes tributo response cadastrados externamente
      | Sku       | Valor Icms | Valor Pis | Valor Difaul | Valor Fcp Icms |
      | 547170100 | 78.00      | 32.00     | 56.00        | 35.00          |
      | 557882194 | 2.00       | 25.00     | 48.00        | 59.00          |
      | 557282711 | 63.00      | 9.00      | 80.00        | 72.00          |
    E que existam os seguintes authorize response cadastrados externamente
      | Nfe Key                                      | Invoice Number | Issuance Date           | Invoice                                                      |
      | 43210392754738001134550040000159551330074448 | 0074448        | 2022-11-11T15:50:10.851 | NDMyMTAzOTI3NTQ3MzgwMDExMzQ1NTAwNDAwMDAxNTk1NTEzMzAwNzQ0NDg= |

  Cenario: 01 - Processar autorizacao venda valida - Sucesso
    Dado que existam os seguintes canal response cadastrados externamente
      | Mensagem                                   |
      | Venda 2312529489023-1 recebida com sucesso |
    E que existam as vendas cadastradas
      | Canal | Codigo Empresa | Codigo Loja | Numero Pdv | Numero Pedido | Numero Ordem Externo | Valor Total | Quantidade Itens | Venda Request                             | Data Atualizacao    | Data Requisicao         | Chave NFE | Numero Nota | Data Emissao | Pdf | Situacao | Motivo                                  |
      | APP   | 1              | 1           | 501        | 200010710363  | 2312529489023-1      | 387.44      | 6                | /fixture/VendaRequest-23125294890231.json | 2022-11-11T14:47:10 | 2022-11-11T14:37:56.194 |           |             |              |     | ERRO     | Venda 2312529489023-1 recebida com erro |
    Quando processar autorizacao venda
    Entao deveria existir as seguintes vendas na base
      | Id | Canal | Codigo Empresa | Codigo Loja | Numero Pdv | Numero Pedido | Numero Ordem Externo | Valor Total | Quantidade Itens | Venda Request                             | Data Atualizacao    | Data Requisicao         | Chave NFE                                    | Numero Nota | Data Emissao            | Pdf                                                          | Situacao   | Motivo                                  |
      | 1  | APP   | 1              | 1           | 501        | 200010710363  | 2312529489023-1      | 387.44      | 6                | /fixture/VendaRequest-23125294890231.json | 2022-11-11T14:47:10 | 2022-11-11T14:37:56.194 |                                              |             |                         |                                                              | ERRO       | Venda 2312529489023-1 recebida com erro |
      | 2  | APP   | 1              | 1           | 501        | 200010710363  | 2312529489023-1      | 387.44      | 6                | /fixture/VendaRequest-23125294890231.json | 2022-11-11T15:47:10 | 2022-11-11T15:37:56.194 | 43210392754738001134550040000159551330074448 | 0074448     | 2022-11-11T15:50:10.851 | NDMyMTAzOTI3NTQ3MzgwMDExMzQ1NTAwNDAwMDAxNTk1NTEzMzAwNzQ0NDg= | PROCESSADO |                                         |
    E deveria enviar para o endpoint authorize as informacoes de request esperadas
      | Order Number | External Order Number |
      | 200010710363 | 2312529489023-1       |
    E deveria enviar para o endpoint authorize as informacoes de customer esperadas
      | Id     | Name                       | Document    | Document Type | Person Type | Address                   | Address Number | Address Complement | District          | City   | State | Country | Zip Code | Ibge Code | Phone Number    | Email                                |
      | 123456 | Givaldo Santos Vasconcelos | 70420816097 | CPF           | F           | Travessa Francisco Vieira | 11             | Apto 405           | Trapiche da Barra | Maceió | AL    | BR      | 57010460 | 7162435   | (82) 36774-7713 | givaldo.santos.vasconcelos@gmail.com |
    E deveria enviar para o endpoint authorize as informacoes de product esperadas
      | Sku       | Amount | Value | Icms Value | Pis Value | Difaul Value | Fcp Icms Value |
      | 547170100 | 3      | 56.91 | 78.00      | 32.00     | 56.00        | 35.00          |
      | 557882194 | 2      | 79.90 | 2.00       | 25.00     | 48.00        | 59.00          |
      | 557282711 | 1      | 56.91 | 63.00      | 9.00      | 80.00        | 72.00          |
    E deveria enviar para o endpoint callback-venda as informacoes de request esperadas
      | Numero Pedido | Numero Ordem Externo | Chave NFE                                    | Numero Nota | Data Emissao            | Pdf                                                          | Status     |
      | 200010710363  | 2312529489023-1      | 43210392754738001134550040000159551330074448 | 0074448     | 2022-11-11T15:50:10.851 | NDMyMTAzOTI3NTQ3MzgwMDExMzQ1NTAwNDAwMDAxNTk1NTEzMzAwNzQ0NDg= | PROCESSADO |

#  Cenario: 02 - Processar autorizacao venda com erro aleatorio - Fluxo excepcional
#    Dado que existam os seguintes canal response cadastrados externamente
#      | Mensagem                                |
#      | Venda 2312529489023-1 recebida com erro |
#    Quando processar autorizacao venda
#    Entao deveria existir as seguintes vendas na base
#      | Id | Canal | Codigo Empresa | Codigo Loja | Numero Pdv | Numero Pedido | Numero Ordem Externo | Valor Total | Quantidade Itens | Venda Request                             | Data Atualizacao    | Data Requisicao         | Chave NFE | Numero Nota | Data Emissao | Pdf | Situacao | Motivo                                  |
#      | 1  | APP   | 1              | 1           | 501        | 200010710363  | 2312529489023-1      | 387.44      | 6                | /fixture/VendaRequest-23125294890231.json | 2022-11-11T15:47:10 | 2022-11-11T15:37:56.194 |           |             |              |     | ERRO     | Venda 2312529489023-1 recebida com erro |
#    E deveria enviar para o endpoint authorize as informacoes de request esperadas
#      | Order Number | External Order Number |
#      | 200010710363 | 2312529489023-1       |
#    E deveria enviar para o endpoint authorize as informacoes de customer esperadas
#      | Id     | Name                       | Document    | Document Type | Person Type | Address                   | Address Number | Address Complement | District          | City   | State | Country | Zip Code | Ibge Code | Phone Number    | Email                                |
#      | 123456 | Givaldo Santos Vasconcelos | 70420816097 | CPF           | F           | Travessa Francisco Vieira | 11             | Apto 405           | Trapiche da Barra | Maceió | AL    | BR      | 57010460 | 7162435   | (82) 36774-7713 | givaldo.santos.vasconcelos@gmail.com |
#    E deveria enviar para o endpoint authorize as informacoes de product esperadas
#      | Sku       | Amount | Value | Icms Value | Pis Value | Difaul Value | Fcp Icms Value |
#      | 547170100 | 3      | 56.91 | 78.00      | 32.00     | 56.00        | 35.00          |
#      | 557882194 | 2      | 79.90 | 2.00       | 25.00     | 48.00        | 59.00          |
#      | 557282711 | 1      | 56.91 | 63.00      | 9.00      | 80.00        | 72.00          |
#    E deveria enviar para o endpoint callback-venda as informacoes de request esperadas
#      | Numero Pedido | Numero Ordem Externo | Chave NFE                                    | Numero Nota | Data Emissao            | Pdf                                                          | Status     |
#      | 200010710363  | 2312529489023-1      | 43210392754738001134550040000159551330074448 | 0074448     | 2022-11-11T15:50:10.851 | NDMyMTAzOTI3NTQ3MzgwMDExMzQ1NTAwNDAwMDAxNTk1NTEzMzAwNzQ0NDg= | PROCESSADO |