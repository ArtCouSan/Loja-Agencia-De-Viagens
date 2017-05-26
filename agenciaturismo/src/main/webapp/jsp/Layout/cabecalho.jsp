<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>

    <c:when test="${usuario.acesso == 'MASTER'}">
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <ul class="nav navbar-nav">
                    <li ><i class="glyphicon glyphicon-user"><p style="color: white">${usuario.nome}</p></i></li>
                    <li ><a href="logout"><i class="glyphicon glyphicon-user">Sair</i></a></li>
                    <li ><a href="CadastroCliente"><i class="glyphicon glyphicon-user">Cadastrar Cliente</i></a></li>
                    <li ><a href="CadastroFuncionario"><i class="glyphicon glyphicon-user">Cadastrar Funcionario</i></a></li>
                    <li ><a href="CadastroHotel"><i class="glyphicon glyphicon-header">Cadastrar Hotel</i></a></li>
                    <li ><a href="CadastroVoo" ><i class="glyphicon glyphicon-plane">Cadastrar Voo</i></a></li>
                    <li ><a href="CadastroUsuario"><i class="glyphicon glyphicon-user">Cadastrar Usuario</i></a></li>
                    <li ><a href="BuscaCliente"><i class="glyphicon glyphicon-cog">Busca Cliente</i></a></li>
                    <li ><a href="BuscaFuncionario"><i class="glyphicon glyphicon-cog">Busca Funcionario</i></a></li>
                    <li ><a href="BuscaHotel"><i class="glyphicon glyphicon-cog">Busca Hotel</i></a></li>
                    <li ><a href="BuscaVoo"><i class="glyphicon glyphicon-cog">Busca Voo</i></a></li>
                    <li ><a href="BuscaUsuario"><i class="glyphicon glyphicon-cog">Busca Usuario</i></a></li>
                    <li ><a href="PreVenda"><i class="glyphicon glyphicon-shopping-cart">Venda</i></a></li>
                </ul>
            </div>
        </nav>
    </c:when>

    <c:when test="${usuario.acesso == 'BASICO'}">
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <ul class="nav navbar-nav">
                    <li ><i class="glyphicon glyphicon-user"><p style="color: white">${usuario.nome}</p></i></li>
                    <li ><a href="logout"><i class="glyphicon glyphicon-user">Sair</i></a></li>
                    <li ><a href="CadastroCliente"><i class="glyphicon glyphicon-user">Cadastrar Cliente</i></a></li>
                    <li ><a href="BuscaCliente"><i class="glyphicon glyphicon-cog">Busca Cliente</i></a></li>
                    <li ><a href="BuscaHotel"><i class="glyphicon glyphicon-cog">Busca Hotel</i></a></li>
                    <li ><a href="BuscaVoo"><i class="glyphicon glyphicon-cog">Busca Voo</i></a></li>
                    <li ><a href="PreVenda"><i class="glyphicon glyphicon-shopping-cart">Venda</i></a></li>
                </ul>
            </div>
        </nav>
    </c:when>
</c:choose>
