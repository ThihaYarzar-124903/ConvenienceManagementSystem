<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Owner Page</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
    
   <style>
    .logout:hover{
        background-color:red;
        cursor:pointer;
    }
    .form-control {
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        transition: box-shadow 0.3s ease;
    }
    .form-control:focus {
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.3), 0 0 10px rgba(70, 130, 180, 0.6);
    }
	</style>

</head>
<body>
<nav>
    <div class="dadhoboard">
        <div>
            <h3>Owner Dashboard</h3>
        </div>
        <div>
            <a class="logout btn btn-light" style="cursor:pointer; float: right; margin-top: 10px;  padding: 8px 15px; border-radius: 5px; " onclick="showLogoutConfirmation()">Logout</a>
        </div>
    </div>
</nav>
<div class="sidebar">
    <div class="first">
        <ul>
            <li><a href="/ConvenienceManagementSystem/users/">Staff Management</a></li>
            <li><a href="/ConvenienceManagementSystem/categorys/">Category Management</a></li>
            <li><a href="/ConvenienceManagementSystem/products/">Product Management</a></li>
            <li><a href="/ConvenienceManagementSystem/discounts/">Discount Management</a></li>
            <li><a href="/ConvenienceManagementSystem/details">Manage Sales Report</a></li>
            <!-- Add more items as needed -->
        </ul>
    </div>
    <div class="second">
        <div class="bg-white pb-4 mt-3 m-auto" style="width: 95%;">   
            <div>   
                <nav class="p-3 z-3 bg-white shadow p-3 shadow">   
                    <h4>Dashboard</h4>  
                </nav>  
            </div>  
            <div class="d-flex mt-4 justify-content-around text-center">    
                <div class="flex-column rounded p-2 d-flex bg-white shadow justify-content-between w-25" style="height: 150px;">   
                    <h3 class="">Total Categories</h3>    
                    <h2>${categoryCount}</h2>       
                    <a href="/ConvenienceManagementSystem/categorys/" class="pt-2 pb-2 text-bg-info" style="text-decoration:none;">View Categories</a>    
                </div>
                <div class="flex-column p-2 rounded d-flex bg-white bg-white shadow justify-content-between w-25 " style="height: 150px;">
                    <h3>Total Products</h3>
                    <h2>${productCount}</h2>
                    <a href="/ConvenienceManagementSystem/products/" class="pt-2 pb-2 text-bg-info" style="text-decoration:none;">View Products</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Logout confirmation modal -->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="logoutModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="logoutModalLabel">Logout Confirmation</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are you sure you want to logout?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="cancelDelete()">Cancel</button>
                <button type="button" class="btn btn-primary" onclick="logout()">Logout</button>
            </div>
        </div>
    </div>
</div>

<script>
    function showLogoutConfirmation() {
        $('#logoutModal').modal('show');
    }

    function logout() {
        window.location.href = "/ConvenienceManagementSystem/logout/";
    }

    function cancelDelete() {
        $('#logoutModal').modal('hide');
    }
</script>
</body>
</html>
