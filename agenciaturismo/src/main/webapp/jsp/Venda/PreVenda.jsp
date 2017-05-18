<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Validar Cliente</title>
        <link type="text/css" rel="stylesheet" type="text/css" href="./bootstrap/css/bootstrap-theme.min.css" />
        <link type="text/css"  href="./bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    </head>
    <body>
        <c:import url="/jsp/Layout/cabecalho.jsp"/>
        <div class="panel-body">
            <form action="${pageContext.request.contextPath}/PreVenda" method="post" class="form-horizontal" data-toggle="validator">
                <h1 class="text-center">Validação do cliente</h1>
                <div class="form-group">
                    <label for="example-text-input" class="control-label col-md-4">CPF:</label>
                    <div class="controls col-md-5">
                        <input class="form-control cpf-mask" placeholder="000.000.000-00" name="cpf" type="text" id="example-text-input" required>
                    </div>
                </div>
                <div class="col-md-12 text-center">
                    <button type="submit" class="btn btn-primary"><span class="">Validar</span></button>
                </div>
            </form>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="./bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>
