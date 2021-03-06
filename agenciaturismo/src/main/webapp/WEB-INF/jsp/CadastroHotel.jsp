<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" type="text/css" href="./bootstrap/css/bootstrap-theme.min.css" />
        <link type="text/css"  href="./bootstrap/css/bootstrap.min.css" rel="stylesheet" />
        <link type="text/css"  href="./bootstrap/css/particular.css" rel="stylesheet" />
        <script src="./bootstrap/js/camposMascara.js" type="text/javascript" ></script>
        <script src="./bootstrap/js/aceitacoes.js" type="text/javascript" ></script>
        <title>Cadastro de Hotel</title>
    </head>
    <body>
        <c:import url="./cabecalho.jsp"/>
        <div class="panel-body">
            <form action="CadastroHotel" method="post" class="form-horizontal">
                <div class="form-group ">
                    <c:if test="${erroNome_hotel}">
                        <script>alert("Erro no nome!")</script>
                    </c:if>
                    <label for="example-text-input" class="control-label col-md-4">* Nome do Hotel <span class="glyphicon glyphicon-home"></span> :</label>
                    <div class="controls col-md-5">
                        <input class="form-control" maxlength="50" name="nome_hotel" placeholder="Insira o nome do hotel" type="text" id="example-text-input" required> 
                    </div>
                </div>
                <div class="form-group">
                    <c:if test="${erroData_entrada}">
                        <script>alert("Erro na data!")</script>
                    </c:if>
                    <label for="example-date-input" class="control-label col-md-4" >* Data de entrada <span class="glyphicon glyphicon-calendar"></span> :</label>
                    <div class="controls col-md-5">
                        <input class="form-control" min="2017-06-07" name="data_entrada" placeholder="Insira a data de entrada" type="date" id="example-date-input" required>
                    </div>
                </div>
                <div class="form-group">
                    <c:if test="${erroData_saida}">
                        <script>alert("Erro na data de saida!")</script>
                    </c:if>
                    <label for="example-date-input" class="control-label col-md-4" >* Data de saida <span class="glyphicon glyphicon-calendar"></span> :</label>
                    <div class="controls col-md-5">
                        <input class="form-control" min="2017-06-07" name="data_saida" placeholder="Insira a data de saida" type="date" id="example-date-input" required>
                    </div>
                </div>
                <div class="form-group">
                    <c:if test="${erroQuantidade_quartos}">
                        <script>alert("Erro na quantidade de quartos!")</script>
                    </c:if>
                    <label for="example-number-input" class="control-label col-md-4">* Quantidade de Quartos <span class="glyphicon glyphicon-bed"></span> :</label>
                    <div class="controls col-md-5">
                        <input class="form-control" min="1" max="999" type="number" name="quantidade_quartos" placeholder="Insira a quantidade de quartos" id="example-number-input" required>
                    </div>
                </div>
                <div class="form-group">
                    <c:if test="${erroQuantidade_hospedes}">
                        <script>alert("Erro na quantidade de hospedes!")</script>
                    </c:if>
                    <label for="example-number-input" class="control-label col-md-4">* Quantidade de Hospedes <span class="glyphicon glyphicon-user"></span> :</label>
                    <div class="controls col-md-5">
                        <input class="form-control" min="1" max="999" type="number" name="quantidade_hospedes" placeholder="Insira a quantidade de hospedes" id="example-number-input" required>
                    </div>
                </div>
                <div class="form-group">
                    <c:if test="${erroPreco}">
                        <script>alert("Erro no preço!")</script>
                    </c:if>
                    <label for="example-number-input" class="control-label col-md-4">* Preço <span class="glyphicon glyphicon-usd"></span>  :</label>
                    <div class="controls col-md-5">
                        <input class="form-control" type="number" min="1" name="preco" placeholder="Insira o preço" id="example-number-input" required>
                    </div>
                </div>
                <div class="col-lg-offset-4">
                    <button type="submit" style="width: 800px" class="btn btn-primary botao_g"><span class="glyphicon glyphicon-floppy-saved"> Cadastrar</span></button>
                </div>
            </form>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="./bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>
