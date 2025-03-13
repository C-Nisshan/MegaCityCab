<%--
  Created by IntelliJ IDEA.
  User: Nissan
  Date: 3/12/2025
  Time: 11:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.megacitycab.megacitycab.models.Pricing, com.megacitycab.megacitycab.models.Location" %>
<html>
<head>
  <title>Manage Pricing</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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

<!-- Navbar -->
<jsp:include page="/WEB-INF/views/navbar.jsp" />

<div class="main-container">
  <jsp:include page="/WEB-INF/views/admin/sidebar.jsp"/>

  <div class="content">
    <h2>Pricing Management</h2>

    <button type="button" class="btn btn-primary mb-3" data-toggle="modal" data-target="#addPricingModal">
      Add New Pricing
    </button>

    <table class="table table-bordered">
      <thead class="thead-dark">
      <tr>
        <th>ID</th>
        <th>Pickup Location</th>
        <th>Dropoff Location</th>
        <th>Price</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <%
        List<Pricing> pricings = (List<Pricing>) request.getAttribute("pricings");
        List<Location> locations = (List<Location>) request.getAttribute("locations");

        if (pricings != null && !pricings.isEmpty()) {
          for (Pricing pricing : pricings) {
            String pickupName = "", dropoffName = "";

            // Check if locations list is not null before iterating
            if (locations != null) {
              for (Location loc : locations) {
                if (loc.getLocationId() == pricing.getPickupLocation()) {
                  pickupName = loc.getLocationName();
                }
                if (loc.getLocationId() == pricing.getDropoffLocation()) {
                  dropoffName = loc.getLocationName();
                }
              }
            }
      %>
      <tr>
        <td><%= pricing.getPricingId() %></td>
        <td><%= pickupName %></td>
        <td><%= dropoffName %></td>
        <td>Rs. <%= pricing.getPrice() %></td>
        <td>
          <a href="#" class="editPricingBtn"
             data-pricing-id="<%= pricing.getPricingId() %>"
             data-pickup-location="<%= pricing.getPickupLocation() %>"
             data-dropoff-location="<%= pricing.getDropoffLocation() %>"
             data-price="<%= pricing.getPrice() %>"
             data-toggle="modal" data-target="#editPricingModal">
            Edit
          </a>
          <button class="btn btn-danger delete-pricing"
                  data-id="<%= pricing.getPricingId() %>">
            Delete
          </button>
        </td>
      </tr>
      <%
        }
      } else {
      %>
      <tr>
        <td colspan="10">No pricing records found.</td>
      </tr>
      <%
        }
      %>
      </tbody>
    </table>
  </div>
</div>

<!-- Add Pricing Modal -->
<div class="modal fade" id="addPricingModal" tabindex="-1" role="dialog" aria-labelledby="addPricingModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="addPricingModalLabel">Add New Pricing</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form action="" method="post">
          <input type="hidden" name="action" value="create">

          <div class="form-group">
            <label for="pickupLocation">Pickup Location:</label>
            <select class="form-control" id="pickupLocation" name="pickupLocation" required>
              <option value="">Select Pickup Location</option>
              <% if (locations != null) { %>
              <% for (Location location : locations) { %>
              <option value="<%= location.getLocationId() %>"><%= location.getLocationName() %></option>
              <% } %>
              <% } else { %>
              <option value="">No Locations Available</option>
              <% } %>
            </select>
          </div>

          <div class="form-group">
            <label for="dropoffLocation">Dropoff Location:</label>
            <select class="form-control" id="dropoffLocation" name="dropoffLocation" required>
              <option value="">Select Dropoff Location</option>
              <% if (locations != null) { %>
              <% for (Location location : locations) { %>
              <option value="<%= location.getLocationId() %>"><%= location.getLocationName() %></option>
              <% } %>
              <% } else { %>
              <option value="">No Locations Available</option>
              <% } %>
            </select>
          </div>

          <div class="form-group">
            <label for="price">Price:</label>
            <input type="number" class="form-control" id="price" name="price" required>
          </div>

          <button type="submit" class="btn btn-success">Add Pricing</button>
        </form>
      </div>
    </div>
  </div>
</div>

<!-- Update Pricing Modal -->
<div class="modal fade" id="editPricingModal" tabindex="-1" role="dialog" aria-labelledby="editPricingModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="editPricingModalLabel">Update Pricing</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form action="" method="post">
          <input type="hidden" name="action" value="update">
          <input type="hidden" id="editPricingId" name="pricingId">

          <div class="form-group">
            <label for="editPickupLocation">Pickup Location:</label>
            <select class="form-control" id="editPickupLocation" name="pickupLocation" required>
              <% for (Location location : locations) { %>
              <option value="<%= location.getLocationId() %>"><%= location.getLocationName() %></option>
              <% } %>
            </select>
          </div>

          <div class="form-group">
            <label for="editDropoffLocation">Dropoff Location:</label>
            <select class="form-control" id="editDropoffLocation" name="dropoffLocation" required>
              <% for (Location location : locations) { %>
              <option value="<%= location.getLocationId() %>"><%= location.getLocationName() %></option>
              <% } %>
            </select>
          </div>

          <div class="form-group">
            <label for="editPrice">Price:</label>
            <input type="number" class="form-control" id="editPrice" name="price" required>
          </div>

          <button type="submit" class="btn btn-success">Update Pricing</button>
        </form>
      </div>
    </div>
  </div>
</div>

<!-- Bootstrap JS & jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
  $(document).ready(function () {
    $(".delete-pricing").click(function () {
      var pricingId = $(this).data("id");

      if (confirm("Are you sure you want to delete this pricing?")) {
        $.ajax({
          url: "/MegaCityCab_war_exploded/admin/manage_pricing",
          type: "DELETE",
          contentType: "application/json",
          data: JSON.stringify({ id: pricingId }),
          success: function (response) {
            alert(response.trim() === "success" ? "Pricing deleted successfully." : "Failed to delete pricing.");
            location.reload();
          },
          error: function () {
            alert("Error deleting pricing.");
          }
        });
      }
    });

    $(".editPricingBtn").click(function () {
      $("#editPricingId").val($(this).data("pricing-id"));
      $("#editPickupLocation").val($(this).data("pickup-location"));
      $("#editDropoffLocation").val($(this).data("dropoff-location"));
      $("#editPrice").val($(this).data("price"));
      $("#editPricingModal").modal("show");
    });
  });
</script>

<script>
  $(document).ready(function () {
    let existingRoutes = new Set();

    // Populate existing routes from the table
    $("tbody tr").each(function () {
      let pickup = $(this).find("td:nth-child(2)").text().trim();
      let dropoff = $(this).find("td:nth-child(3)").text().trim();
      if (pickup && dropoff) {
        existingRoutes.add(pickup + " - " + dropoff);
      }
    });

    function validatePricingForm(pickupId, dropoffId, isEdit = false) {
      let pickupText = $("#pickupLocation option:selected").text();
      let dropoffText = $("#dropoffLocation option:selected").text();

      // Check if pickup and dropoff locations are the same
      if (pickupId === dropoffId) {
        alert("Pickup and dropoff locations cannot be the same.");
        return false;
      }

      // Check for duplicate routes (if not editing)
      if (!isEdit && existingRoutes.has(pickupText + " - " + dropoffText)) {
        alert("This route already exists. Please choose a different pickup or dropoff location.");
        return false;
      }

      return true;
    }

    // Validate before submitting Add Pricing form
    $("#addPricingModal form").submit(function (e) {
      let pickupId = $("#pickupLocation").val();
      let dropoffId = $("#dropoffLocation").val();

      if (!validatePricingForm(pickupId, dropoffId)) {
        e.preventDefault();
      }
    });

    // Validate before submitting Edit Pricing form
    $("#editPricingModal form").submit(function (e) {
      let pickupId = $("#editPickupLocation").val();
      let dropoffId = $("#editDropoffLocation").val();

      if (!validatePricingForm(pickupId, dropoffId, true)) {
        e.preventDefault();
      }
    });
  });
</script>

<!-- Footer -->
<jsp:include page="/WEB-INF/views/footer.jsp" />

</body>
</html>

