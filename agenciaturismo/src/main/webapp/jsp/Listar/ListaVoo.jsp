<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" type="text/css" href="./bootstrap/css/bootstrap-theme.min.css" />
        <link type="text/css"  href="./bootstrap/css/bootstrap.min.css" rel="stylesheet" />
        <link href="teste.css" rel="stylesheet" type="text/css"/>
        <title>Listar Voos</title>
    </head>
    <body>
        <c:import url="/jsp/Layout/cabecalho.jsp"/>
        <table class="table table-striped">
            <thead>
                <tr class="info">
                    <th>Origem</th>
                    <th>Destino</th>
                    <th>Data de ida</th>
                    <th>Data de volta</th>
                    <th>Quantidade de passagens</th>
                    <th>Preco</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="voos" items="${encontrados}">
                    <tr>
                        <td name="origem"><c:out value="${voos.getOrigem()}"/></td>
                        <td name="destino"><c:out value="${voos.getDestino()}"/></td>
                        <td name="data_ida"><c:out value="${voos.getData_ida()}"/></td>
                        <td name="data_volta"><c:out value="${voos.getData_volta()}"/></td>
                        <td name="quantidade_passagens"><c:out value="${voos.getQuantidade_passagens()}"/></td>
                        <td name="preco"><c:out value="${voos.getPreco()}"/></td>
                        <td><a class="btn btn-danger" href="ExcluiVooServlet?action=edit&id=${voos.getId_voo()}&pesquisa=${pesquisa}"><i class="icon-trash">Remover</i></a></td>
                        <td><a class="btn" href="EditarVoo?action=edit&id=<c:out value="${voos.getId_voo()}"/>"><i class="icon-pencil">Alterar</i></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="./bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>
