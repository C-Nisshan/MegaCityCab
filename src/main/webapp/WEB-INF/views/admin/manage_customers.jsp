<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, com.megacitycab.megacitycab.models.Customer, com.megacitycab.megacitycab.models.User" %>
<%
  List<Customer> customerList = (List<Customer>) request.getAttribute("customerList");
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Customer Management</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
    .main-container {
      display: flex;
    }
    .sidebar {
      width: 250px;
      flex-shrink: 0;
    }
    .content {
      flex-grow: 1;
      padding: 20px;
    }
  </style>
</head>
<body>

<div class="main-container">
  <jsp:include page="/WEB-INF/views/admin/sidebar.jsp"/>

  <div class="content">
    <h2>Customer Management</h2>

    <button type="button" class="btn btn-primary mb-3" data-toggle="modal" data-target="#addCustomerModal">
      Add New Customer
    </button>

    <table class="table table-bordered">
      <thead class="thead-dark">
      <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Name</th>
        <th>Address</th>
        <th>Telephone</th>
        <th>Email</th>
        <th>NIC</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <%
        List<Customer> customers = (List<Customer>) request.getAttribute("customers");
        if (customers != null && !customers.isEmpty()) {
          for (Customer customer : customers) {
            User user = customer.getUser();
      %>
      <tr>
        <td><%= customer.getCustomerId() %></td>
        <td><%= user.getUsername() %></td>
        <td><%= user.getName() %></td>
        <td><%= user.getAddress() %></td>
        <td><%= user.getTelephone() %></td>
        <td><%= user.getEmail() %></td>
        <td><%= user.getNic() %></td>
        <td>
          <a href="#" class="editCustomerBtn"
             data-driver-id="<%= customer.getCustomerId() %>"
             data-user-id="<%= user.getId()%>"
             data-username="<%= user.getUsername() %>"
             data-name="<%= user.getName() %>"
             data-address="<%= user.getAddress() %>"
             data-nic="<%= user.getNic() %>"
             data-telephone="<%= user.getTelephone() %>"
             data-email="<%= user.getEmail() %>"
             data-toggle="modal" data-target="#updateCustomerModal">
            Edit
          </a>
          <button class="btn btn-danger delete-customer"
                  data-id="<%= customer.getCustomerId() %>"
                  data-user-id="<%= user.getId() %>">
            Delete
          </button>
        </td>
      </tr>
      <%
        }
      } else {
      %>
      <tr>
        <td colspan="10">No Customers found.</td>
      </tr>
      <%
        }
      %>
      </tbody>
    </table>
  </div>
</div>

<!-- Add Driver Modal -->
<div class="modal fade" id="addCustomerModal" tabindex="-1" role="dialog" aria-labelledby="addCustomerModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="addCustomerModalLabel">Add New Customer</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form action="" method="post">
          <input type="hidden" name="action" value="create">
          <input type="hidden" name="role" value="Customer">

          <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" name="username" required>
          </div>

          <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password" required>
          </div>

          <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" name="name" required>
          </div>

          <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" class="form-control" id="address" name="address" required>
          </div>

          <div class="form-group">
            <label for="nic">NIC:</label>
            <input type="text" class="form-control" id="nic" name="nic" required>
          </div>

          <div class="form-group">
            <label for="phone">Phone:</label>
            <input type="text" class="form-control" id="phone" name="telephone" required>
          </div>

          <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" name="email">
          </div>

          <button type="submit" class="btn btn-success">Add Customer</button>
        </form>
      </div>
    </div>
  </div>
</div>

<!-- Bootstrap JS & jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- Update Customer Modal -->
<div class="modal fade" id="updateCustomerModal" tabindex="-1" role="dialog" aria-labelledby="updateCustomerModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="updateDriverModalLabel">Update Customer</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form action="" method="post">
          <input type="hidden" name="action" value="update">
          <input type="hidden" id="editCustomerId" name="customer_id">
          <input type="hidden" id="editUserId" name="user_id">

          <div class="form-group">
            <label for="editUsername">Username:</label>
            <input type="text" class="form-control" id="editUsername" name="username" required>
          </div>

          <div class="form-group">
            <label for="editPassword">Password:</label>
            <input type="password" class="form-control" id="editPassword" name="password" required>
          </div>

          <div class="form-group">
            <label for="editName">Name:</label>
            <input type="text" class="form-control" id="editName" name="name" required>
          </div>

          <div class="form-group">
            <label for="editAddress">Address:</label>
            <input type="text" class="form-control" id="editAddress" name="address" required>
          </div>

          <div class="form-group">
            <label for="editNic">NIC:</label>
            <input type="text" class="form-control" id="editNic" name="nic" required>
          </div>

          <div class="form-group">
            <label for="editPhone">Phone:</label>
            <input type="text" class="form-control" id="editPhone" name="telephone" required>
          </div>

          <div class="form-group">
            <label for="editEmail">Email:</label>
            <input type="email" class="form-control" id="editEmail" name="email">
          </div>

          <button type="submit" class="btn btn-success">Update Customer</button>
        </form>
      </div>
    </div>
  </div>
</div>

<script>
  $(document).ready(function () {
    $(".delete-customer").click(function () {
      var customerId = $(this).data("id");
      var userId = $(this).data("user-id");

      if (!customerId || !userId) {
        alert("Error: Missing Customer or user ID!");
        return;
      }

      if (confirm("Are you sure you want to delete this customer?")) {
        $.ajax({
          url: "/MegaCityCab_war_exploded/admin/manage_customers",
          type: "DELETE",
          contentType: "application/json",
          data: JSON.stringify({ id: customerId, userId: userId }),
          success: function (response) {
            if (response.trim() === "success") {
              alert("Customer deleted successfully.");
              location.reload();
            } else {
              alert("Failed to delete customer.");
            }
          },
          error: function () {
            alert("Error deleting customer.");
          }
        });
      }
    });
  });
</script>

<script>
  document.addEventListener("DOMContentLoaded", function () {
    const editButtons = document.querySelectorAll(".editCustomerBtn");

    editButtons.forEach(button => {
      button.addEventListener("click", function () {
        document.getElementById("editCustomerId").value = this.dataset.customerId;
        document.getElementById("editUserId").value = this.dataset.userId;
        document.getElementById("editUsername").value = this.dataset.username;
        document.getElementById("editName").value = this.dataset.name;
        document.getElementById("editAddress").value = this.dataset.address;
        document.getElementById("editNic").value = this.dataset.nic;
        document.getElementById("editPhone").value = this.dataset.telephone;
        document.getElementById("editEmail").value = this.dataset.email;

        $("#updateCustomerModal").modal("show");
      });
    });
  });
</script>

</body>
</html>