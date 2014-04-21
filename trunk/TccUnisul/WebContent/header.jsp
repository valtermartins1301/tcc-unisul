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
</head>
<body>
    <div id="wrapper">
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <a class="navbar-brand" href="index.jsp">Inicio</a>
            </div>
            
            <div class="navbar-header dropdown">
                <a class="navbar-brand dropdown-toggle" data-toggle="dropdown">Cadastros<span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
				    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Clientes</a></li>
				    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Produtos</a></li>
				    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Entregadores</a></li>				    
			  	</ul>
            </div>
            
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
            <!-- /.navbar-top-links -->
   </div>
   <!-- /#wrapper -->