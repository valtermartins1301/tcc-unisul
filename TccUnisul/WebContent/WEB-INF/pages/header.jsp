<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>DinnerChief</title>

    <!-- Core CSS - Include with every page -->
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="bootstrap/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="bootstrap/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
        
    <!-- SB Admin CSS - Include with every page -->
    <link href="bootstrap/css/sb-admin.css" rel="stylesheet">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
</head>
<body>
    <div id="wrapper">
        <nav class="navbar navbar-custom navbar-fixed-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <a class="navbar-brand" href="index">Inicio</a>
            </div>
            
            <ul class="nav navbar-top-links navbar-header">
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        Cadastros <i class="glyphicon glyphicon-chevron-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="listaClientes">Clientes</a></li>
					    <li role="presentation"><a role="menuitem" tabindex="-1" href="listaProdutos">Produtos</a></li>
					    <li role="presentation"><a role="menuitem" tabindex="-1" href="listaEntregadores">Entregadores</a></li>		
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            
            <ul class="nav navbar-top-links navbar-right">
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="glyphicon glyphicon-user"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="login.jsp"><i class="glyphicon glyphicon-off"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
        </nav>
            <!-- /.navbar-top-links -->
   </div>
   <!-- /#wrapper -->