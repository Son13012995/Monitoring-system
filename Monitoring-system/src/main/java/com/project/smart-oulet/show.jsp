<%@page contentType="text/html" pageEncoding="UTF-8" %>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
         <!DOCTYPE html>
         <html lang="en">
 
         <head>
             <meta charset="utf-8" />
             <meta http-equiv="X-UA-Compatible" content="IE=edge" />
             <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
             <meta name="description" content="Monitoring-system" />
             <meta name="author" content="4 Monitoring-system" />
             <title>Dashboard Smart Outlet</title>
 <<<<<<< HEAD
             <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
             <link href="css/styles.css" rel="stylesheet" />
 =======
             <link href="/css/style.css" rel="stylesheet" />
 >>>>>>> d3fd5ea (refactor : update algorithm AggregatedLog)
             <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
         </head>
 
         <body class="sb-nav-fixed">
             <jsp:include page="../layout/header.jsp" />
             <div id="layoutSidenav">
                 <jsp:include page="../layout/sidebar.jsp" />
                 <div id="layoutSidenav_content">
                     <main>
                         <div class="container-fluid px-4">
                             <h1 class="mt-4">Manage Devices</h1>
                             <ol class="breadcrumb mb-4">
                                 <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                 <li class="breadcrumb-item active">Smart Outlet</li>
                             </ol>
                             <div class="mt-5">
                                 <div class="row">
                                     <div class="col-12 mx-auto">
                                         <div class="d-flex justify-content-between">
                                             <h3>Table devices</h3>
                                             <a href="/admin/user/create" class="btn btn-primary">Create Smart Outlet</a>
                                         </div>
 
                                         <hr />
                                         <table class=" table table-bordered table-hover">
                                             <thead>
                                                 <tr>
                                                     <th>ID</th>
                                                     <th>Name</th>
 
                                                 </tr>
                                             </thead>
                                             <tbody>
                                                 <c:forEach var="smartOutlet" items="${smartOutlets}">
 
                                                     <tr>
                                                         <th>${smartOutlet.id}</th>
                                                         <td>${smartOutlet.name}</td>
                                                         <td>
                                                             <a href="/admin/smart-outlet/${smartOutlet.id}"
                                                                 class="btn btn-success">View</a>
                                                             <a href="/admin/smart-outlet/update/${smartOutlet.id}"
                                                                 class="btn btn-warning  mx-2">Update</a>
                                                             <a href="/admin/smart-outlet/delete/${smartOutlet.id}"
                                                                 class="btn btn-danger">Delete</a>
                                                         </td>
                                                     </tr>
 
                                                 </c:forEach>
 
                                             </tbody>
                                         </table>
                                     </div>
 
                                 </div>
 
                             </div>
                         </div>
                     </main>
                     <jsp:include page="../layout/footer.jsp" />
                 </div>
             </div>
             <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                 crossorigin="anonymous"></script>
             <script src="/js/scripts.js"></script>
 
         </body>
 