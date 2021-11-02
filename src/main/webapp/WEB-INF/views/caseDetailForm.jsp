<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request Contract</title>
</head>
<body class="container align-items-center">
	<jsp:include page="banner.jsp" />
	<jsp:include page="menu.jsp" />

	<hr />

	

	<div class="row">
		<div class="col-2 col-md-2 col-sm-2">

		</div>
		<div class="col-4 col-sm-8 col-md-6 ">

			<div class="card">

				<div class="card-header bg-success">Contract Details</div>
				<div class="card-body">

					<form action="generateQuote" method="get" autocomplete="off"
						class="justify-content-center">

						<div class="form-group">
							<lable>ContractName</lable>
							<input name="contractName" class="form-control" required="required" />
						</div>

						<div class="form-group">
							<lable>CompanyName</lable>
							<input name="companyName" class="form-control" required="required" />
						</div>


						<div class="form-group">
							<lable>ContractType</lable>
							<input name="contractType" class="form-control" required="required" />
						</div>
						<div class="form-group">
							<lable>AmountUSD</lable>
							<input type="number" name="amountUSD" class="form-control" required="required"  />
						</div>
						<div class="form-group">
							<lable>CompanyType</lable>
							<input name="companyType" class="form-control" required="required"  />
						</div>
						<div class="form-group">
							<label>Category<label>
							
							 <select name="category" id="category" class="form-control" style="width: 500px;" required="required" />
                          <option value="Support">Support</option>
                          <option value="Development">Development</option>
						</select>
</div>
<div class="form-group">
							

						<div class="form-group">
							<span>${msg}</span>
						</div>

						<div class="text-center">
							<button class="btn btn-primary">Submit</button>
							<button class="btn btn-danger">Cancel</button>
						</div>



					</form>

				</div>

			</div>
		</div>
	</div>



</body>
</html>