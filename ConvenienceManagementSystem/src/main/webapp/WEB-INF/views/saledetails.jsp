<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product Details</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
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
</head>
<body>

<nav>
    <div class="dadhoboard"> 
        <div> 
            <h3>Sale Dashboard</h3> 
        </div> 
        <div> 
            <a class="logout btn btn-light" style="cursor:pointer; float: right; margin-top: 10px;  padding: 8px 15px; border-radius: 5px; " onclick="showLogoutConfirmation()">Logout</a>
        </div>
    </div>
 </nav>
<div class="sidebar">
    <div class="first">
        <ul>
           <li><a href="/ConvenienceManagementSystem/staff">Dashboard</a></li>
           <li><a href="/ConvenienceManagementSystem/productDetails">Make Sale</a></li>
           <li><a href="/ConvenienceManagementSystem/viewproduct/">Products</a></li>
           <li><a href="/ConvenienceManagementSystem/categories/">Categories</a></li>
           <li><a href="/ConvenienceManagementSystem/salesreports">Generate Sale Report</a></li>
                <!-- Add more items as needed -->
        </ul>
    </div>
    <div class="second">

<div class="container mt-4">
 <div class="bg-white p-3 text-dark shadow m-auto border-radius-1" style="width: 95%;">
    <div class="row">
    <div class="mt-3 p-3 bg-light shadow">Item added into </div>
        <!-- Product Code Input -->
        <div class="col-md-6">
            <label for="productCode" class="form-label">Code</label>
            <input type="text" class="form-control" id="productCode" placeholder="Enter Product Code" />
        </div>
        <!-- Quantity Input and Buttons -->
        <div class="col-md-6">
            <label for="quantity" class="form-label">Quantity</label>
            <div class="input-group">
                <input type="number" class="form-control" id="quantity" placeholder="Enter Quantity" />
                <button type="button" class="btn btn-primary" onclick="getProduct()" style="margin-left:5px">ADD ITEM</button>
<!--                 <button type="button" class="btn btn-success" onclick="print()">print</button>
 -->               <!--  <a href="/ConvenienceManagementSystem/report"><button type="button" class="btn btn-success" onclick="print()">print</button></a> -->
            </div>
        </div>
    </div>

    <!-- Transaction Slip Table for Details -->
    <h2 style="color: red;">Order Item Details</h2>
    <table class="table mt-3">
        <thead>
            <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Discount</th>
                <th>Quantity</th>
                <th>Amount</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody id="productDetailstbody">
        </tbody>
        <tfoot>
            <tr id="totalAmountRow">
                <td colspan="4"><strong>Total Amount</strong></td>
                <td><strong><span id="totalAmount">0.00</span> MMK</strong></td>
            </tr>
        </tfoot>
    </table>
    <div class="d-flex justify-content-end">
    	<button type="button" class="btn btn-success" onclick="sale()">SAVE</button>
    </div>

    <!-- Transaction Slip Modal -->
    <div class="modal fade" id="transactionSlipModal" tabindex="-1" aria-labelledby="transactionSlipModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="transactionSlipModalLabel">Transaction Slip Report</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <!-- Content of the Transaction Slip Report -->
                    <table class="table mt-3">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Discount</th>
                                <th>Quantity</th>
                                <th>Amount</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody id="modalProductDetailstbody">
                        </tbody>
                        <tfoot>
                            <tr id="modalTotalAmountRow">
                                <td colspan="4"><strong>Total Amount</strong></td>
                                <td><strong><span id="modalTotalAmount">0.00</span> MMK</strong></td>
                            </tr>
                        </tfoot>
                    </table>
                    
                </div>
                 
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
					 <a href="/ConvenienceManagementSystem/report"><button type="button" class="btn btn-success" onclick="print()">print</button></a>                </div>
          
            </div>
           
        </div>
    </div>
    
</div>
</div>
  

</div>
</div>

<div class="modal" tabindex="-1" role="dialog" id="logoutModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Logout Confirmation</h5>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to logout?</p>
            </div>
            <div class="modal-footer">
                <a href="#" class="btn btn-primary" onclick="logout()">Logout</a>
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="cancelDelete()">Cancel</button>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Include jsPDF library for PDF generation -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script>
<!-- Include toastr.js for toast messages -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
<script src="https://cdn.rawgit.com/davidshimjs/qrcodejs/gh-pages/qrcode.min.js"></script>

<script>
    var totalAmount = 0; // Variable to store the total amount
    let saleDetails=[];

    // Function to fetch product details
/*       function getProduct() {
        var productCode = document.getElementById('productCode').value;
        var quantity = document.getElementById('quantity').value;

        if (productCode.trim() !== '') {
            // Fetch product details using AJAX
            fetch('/ConvenienceManagementSystem/productDetails/fetch/' + productCode)
                .then(response => response.json())
                .then(data => {
                    if (data.name != null && Object.keys(data).length > 0) {
                        // Check if requested quantity is less than or equal to product_qty                     
                        if (parseInt(quantity) > data.product_qty) {
                            showToastr('warning', 'Requested quantity exceeds available stock.');
                        } else {
                        	console.log(data);
                            displayProductDetails(data, quantity);
                            calculateTotalAmount();
                            showTransactionSlip(data, quantity);
                            document.getElementById('productCode').value = "";
                            document.getElementById('quantity').value = "";
                        }
                    } else {
                        showToastr('error', 'Product code not found.');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        } else {
            showToastr('error', 'Please enter a Product Code.');
        }
    }  */
    
    function getProduct() {
        var productCode = document.getElementById('productCode').value;
        var quantity = document.getElementById('quantity').value;

        if (productCode.trim() !== '') {
            if (quantity.trim() !== '') {
                // Fetch product details using AJAX
                fetch('/ConvenienceManagementSystem/productDetails/fetch/' + productCode)
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Network response was not ok');
                        }
                        return response.json();
                    })
                    .then(data => {
                        // Handle successful response
                        if (data.name != null && Object.keys(data).length > 0) {
                            // Check if requested quantity is less than or equal to product_qty                     
                            if (parseInt(quantity) > data.product_qty) {
                                showToastr('warning', 'Requested quantity exceeds available stock.');
                            } else {
                                console.log(data);
                                displayProductDetails(data, quantity);
                                calculateTotalAmount();
                                showTransactionSlip(data, quantity);
                                document.getElementById('productCode').value = "";
                                document.getElementById('quantity').value = "";
                            }
                        } else {
                            showToastr('error', 'Product code not found.');
                        }
                    })
                    .catch(error => {
                        console.error('Error fetching product details:', error);
                        showToastr('error', 'Error fetching product details. Please try again later.');
                    });
            } else {
                
                showToastr('error', 'Please enter a Product Quantity.');
            }
        } else {
            showToastr('error', 'Please enter a Product Code.');
        }
    }

    
    // Function to display product details in the table
    function displayProductDetails(productDetails, quantity) {
        var existingRow = $("#productDetailstbody tr:contains('" + productDetails.name + "')");

        if (existingRow.length > 0) {
            // Update the quantity and amount for existing product
            var existingQuantity = parseInt(existingRow.find('td:nth-child(4)').text());
            var newQuantity = existingQuantity + parseInt(quantity);
            existingRow.find('td:nth-child(4)').text(newQuantity);

            var existingAmount = parseFloat(existingRow.find('td:nth-child(5)').text().replace('MMK', ''));
            var newAmount = calculateAmount(productDetails.sold_price, productDetails.product_discount, newQuantity);
            existingRow.find('td:nth-child(5)').text(newAmount.toFixed(2) + ' MMK');

            totalAmount += newAmount - existingAmount;
        } else {
            // Add a new row for the product
            var amount = calculateAmount(productDetails.sold_price, productDetails.product_discount, quantity);
            totalAmount += amount;

            if (parseInt(quantity) > productDetails.product_qty) {
                $("#productDetailstbody").append('<tr data-name="' + productDetails.name + '" data-quantity="' + quantity + '"><td colspan="5"><span class="badge bg-warning">Warning: Requested quantity exceeds available stock</span></td></tr>');
            } else {
            	let saleDetail={
            		products_id:productDetails.id,
            		discounts_id:productDetails.discount_id,
            		qty:quantity
            	};
            	saleDetails.push(saleDetail);
                var newRow = $('<tr data-name="' + productDetails.name + '" data-quantity="' + quantity + '"><td>' + productDetails.name + '</td><td>' + productDetails.sold_price + '</td><td>' + productDetails.product_discount + '</td><td>' + quantity + '</td><td>' + amount.toFixed(2) + ' MMK</td><td><button type="button" class="btn btn-danger btn-sm" onclick="deleteProduct(this)">Remove</button></td></tr>');
                $("#productDetailstbody").append(newRow);

                $("#modalProductDetailstbody").append('<tr data-name="' + productDetails.name + '" data-quantity="' + quantity + '"><td>' + productDetails.name + '</td><td>' + productDetails.sold_price + '</td><td>' + productDetails.product_discount + '</td><td>' + quantity + '</td><td>' + amount.toFixed(2) + ' MMK</td></tr>');
            }
        }

        calculateTotalAmount();
    }

    function calculateAmount(price, discount, quantity) {
        var totalAmount = price * quantity;
        var discountedAmount = totalAmount - (totalAmount * (discount / 100));
        return discountedAmount;
    }

    function calculateTotalAmount() {
        $("#totalAmount").text(totalAmount.toFixed(2));
        $("#modalTotalAmount").text(totalAmount.toFixed(2));
    }

    function showTransactionSlip(productDetails, quantity) {
        var currentDate = new Date();
        var transactionDate = currentDate.toDateString() + ' ' + currentDate.toLocaleTimeString();

        $("#transactionDate").text(transactionDate);

        if (productDetails.hasOwnProperty('productCode')) {
            $("#transactionProductCode").text(productDetails.productCode);
        } else {
            $("#transactionProductCode").text("N/A");
        }

        $("#transactionQuantity").text(quantity);
        var transactionTotalAmount = calculateAmount(productDetails.sold_price, productDetails.product_discount, quantity);
        $("#transactionTotalAmount").text(transactionTotalAmount.toFixed(2));

        $("#transactionSlip").show();
    }

    function showTransactionSlipModal() {
        var currentDate = new Date();
        var transactionDate = currentDate.toDateString() + ' ' + currentDate.toLocaleTimeString();
        var transactionCode = $("#transactionProductCode").text();

        var modalContent = '<h2>Transaction Slip Details</h2>';
        modalContent += '<p>Date: ' + transactionDate + '</p>';
        modalContent += '<table class="table mt-3">';
        modalContent += '<thead><tr><th>Name</th><th>Price</th><th>Discount</th><th>Quantity</th><th>Amount</th><th>Action</th></tr></thead>';
        modalContent += '<tbody>';
        modalContent += $("#modalProductDetailstbody").html();
        modalContent += '</tbody>';
        modalContent += '<tfoot>';
        modalContent += '<tr id="modalTotalAmountRow"><td colspan="4"><strong>Total Amount</strong></td><td><strong><span id="modalTotalAmount">' + totalAmount + '</span> MMK</strong></td></tr>';
        modalContent += '</tfoot>';
        modalContent += '</table>';

        $("#transactionSlipModal .modal-body").html(modalContent);
        $("#transactionSlipModal").modal('show');
    }

    function calculateAmount(price, discount, quantity) {
        var totalAmount = price * quantity;
        var discountedAmount = totalAmount - (totalAmount * (discount / 100));
        return discountedAmount;
    }

    function calculateTotalAmount() {
        $("#totalAmount").text(totalAmount.toFixed(2));
        $("#modalTotalAmount").text(totalAmount.toFixed(2));
    }

    function showTransactionSlip(productDetails, quantity) {
        var currentDate = new Date();
        var transactionDate = currentDate.toDateString() + ' ' + currentDate.toLocaleTimeString();

        $("#transactionDate").text(transactionDate);
        if (productDetails.hasOwnProperty('productCode')) {
            $("#transactionProductCode").text(productDetails.productCode);
        } else {
            $("#transactionProductCode").text("N/A");
        }

        $("#transactionQuantity").text(quantity);
        var transactionTotalAmount = calculateAmount(productDetails.sold_price, productDetails.product_discount, quantity);
        $("#transactionTotalAmount").text(transactionTotalAmount.toFixed(2));

        $("#transactionSlip").show();
    }

    function showTransactionSlipModal() {
        var currentDate = new Date();
        var transactionDate = currentDate.toDateString() + ' ' + currentDate.toLocaleTimeString();
        var productCode = $("#transactionProductCode").text();
        var quantity = $("#transactionQuantity").text();
        var totalAmount = $("#transactionTotalAmount").text();

        var modalContent = '<h2>Transaction Slip Details</h2>';
        modalContent += '<table class="table mt-3">';
        modalContent += '<thead><tr><th>Name</th><th>Price</th><th>Discount</th><th>Quantity</th><th>Amount</th><th>Action</th></tr></thead>';
        modalContent += '<tbody>';
        modalContent += $("#modalProductDetailstbody").html();
        modalContent += '</tbody>';
        modalContent += '<tfoot>';
        modalContent += '<tr id="modalTotalAmountRow"><td colspan="4"><strong>Total Amount</strong></td><td><strong><span id="modalTotalAmount">' + totalAmount + '</span> MMK</strong></td></tr>';
        modalContent += '</tfoot>';
        modalContent += '</table>';

        $("#modalTransactionSlipContent").empty().append(modalContent);
        $("#transactionSlipModal").modal('show');
    }

    function printTransactionSlip() {
        var pdf = new jsPDF();

        var transactionDate = $("#transactionDate").text();
        var transactionProductCode = $("#transactionProductCode").text();
        var transactionQuantity = $("#transactionQuantity").text();
        var transactionTotalAmount = $("#transactionTotalAmount").text();

        var detailsTableData = [];
        $("#modalProductDetailstbody tr").each(function(index, row) {
            var rowData = [];
            $(row).find("td").each(function(index, column) {
                rowData.push($(column).text());
            });
            detailsTableData.push(rowData);
        });

        pdf.text("Transaction Slip Details", 15, 15);
        pdf.text("Date: " + transactionDate, 15, 25);
        pdf.text("Product Code: " + transactionProductCode, 15, 35);
        pdf.text("Quantity: " + transactionQuantity, 15, 45);
        pdf.text("Total Amount: " + transactionTotalAmount + " MMK", 15, 55);

        pdf.autoTable({
            startY: 70,
            head: [['Name', 'Price', 'Discount', 'Quantity', 'Amount']],
            body: detailsTableData,
        });
        pdf.save('transaction_slip.pdf');
    }

    function deleteProduct(button) {
        var row = $(button).closest('tr');
        var quantity = row.find('td:nth-child(4)').text();
        var deletedAmount = row.find('td:nth-child(5)').text().trim();
        deletedAmount = parseFloat(deletedAmount.replace('MMK', ''));      
        row.remove();
        $('#productDetailstbody tr[data-quantity="' + quantity + '"]').remove();

       
        totalAmount -= deletedAmount;
        calculateTotalAmount();
    }

    function showToastr(type, message) {
        toastr.options = {
            closeButton: true,
            progressBar: true,
            positionClass: 'toast-top-right',
            showDuration: 400,
            hideDuration: 1000,
            timeOut: 5000,
        };
        toastr[type](message);
    }
    
    function getSessionUserId() {
        var userId = sessionStorage.getItem('userId');
        return userId;
    }
    
    function login() {
        var userData = getUserData();
        sessionStorage.setItem('userId', userData.userId);
    }
    
    function sale() {
    	var loggedInUserId = getSessionUserId();
    	 
    	var salesData = {
    	        accounts_id:  loggedInUserId, 
    	        total_amount: totalAmount,   	           	       
    	        salesDetails:saleDetails
    	    };
    	console.log(salesData);
    	    
    	    fetch('/ConvenienceManagementSystem/sales', {
    	        method: 'POST',
    	        headers: {
    	            'Content-Type': 'application/json',
    	        },
    	        body: JSON.stringify(salesData),
    	    })
    	    .then(response => response.json())
    	    .then(data => {
    	    	console.log(data);
    	      
    	        if (data.success) {   	            
    	            showToastr('success', 'Transaction inserted successfully');
    	        } else {
    	            
    	            showToastr('error', 'Error inserting transaction');
    	        }
    	    }) 
    	    $('#transactionSlipModal').modal('show');
    }
    
     function print(){
    	fetch('/ConvenienceManagementSystem/print')
	    .then(response => response.json())
	    .then(data => {
	    	console.log(data);       
	        if (data.success) {   	            
	            showToastr('success', 'Transaction Print successfully');
	        } else {	            
	            showToastr('error', 'Error inserting transaction');
	        }
	    }) ;
    	clearData();
    }

    function clearData() {
        
        $("#productDetailstbody").empty();
        $("#modalTotalAmountRow").empty();
       
        $('#transactionSlipModal').modal('hide');
       
    }
    

</script>

</body>
</html>