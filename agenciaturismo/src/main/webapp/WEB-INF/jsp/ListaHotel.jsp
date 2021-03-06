<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" type="text/css" href="./bootstrap/css/bootstrap-theme.min.css" />
        <link type="text/css"  href="./bootstrap/css/bootstrap.min.css" rel="stylesheet" />
        <link type="text/css"  href="./bootstrap/css/particular.css" rel="stylesheet" />
        <script src="./bootstrap/js/event.js" type="text/javascript" ></script>
        <title>Listar Hotel</title>
    </head>
    <body>
        <c:import url="./cabecalho.jsp"/>
        <table class="table table-striped">
            <thead>
                <tr class="info">
                    <th>Nome</th>
                    <th>Data de entrada</th>
                    <th>Data de saida</th>
                    <th>Preco</th>
                    <th>Quantidade de quartos</th>
                    <th>Quantidade de hospedes</th>
                    <th>Remover</th>
                    <th>Editar</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="hoteis" items="${encontrados}">
                    <tr >
                        <td name="nome"><c:out value="${hoteis.getNome()}"/></td>
                        <td name="data_entrada"><c:out value="${hoteis.getData_entrada()}"/></td>
                        <td name="data_saida"><c:out value="${hoteis.getData_saida()}"/></td>
                        <td name="preco"><c:out value="${hoteis.getPreco()}"/></td>
                        <td name="quantidade_quartos"><c:out value="${hoteis.getQuantidade_quartos()}"/></td>
                        <td name="quantidade_hospedes"><c:out value="${hoteis.getQuantidade_hospedes()}"/></td>
                        <td><a class="btn btn-danger" style="width: 100px" href="ExcluiHotelServlet?action=delete&id=${hoteis.getId()}"/><i class="glyphicon glyphicon-trash"> Remover </i></a></td>
                        <td><a class="btn btn-info" style="width: 100px" href="EditarHotel?action=edit&id=${hoteis.getId()}"><i class="glyphicon glyphicon-pencil">Alterar</i></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="./bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>
