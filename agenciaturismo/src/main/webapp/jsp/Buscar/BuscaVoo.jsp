<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <c:import url="./Layout/cabecalho.jsp"/>
    <h1>Digite o campo(s) pela informação que gostaria de buscar</h1>
    <form action="${pageContext.request.contextPath}/BuscaVoo" method="post">
        <label for="txtPesquisa">Pesquisa:</label>  
        <input type="text" name="pesquisa" placeholder="Pesquisa"/>
        <input type="submit" value="Buscar"/>
        <input type="reset" value="Apagar"/>
        <br>
    </form>
    <c:import url="./Layout/rodape.jsp"/>
</body>
</html>
