<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request Quotation</title>
</head>
<body class="container align-items-center">
	<jsp:include page="banner.jsp" />
	<jsp:include page="menu.jsp" />

	<hr />



	<div class="row">
		<div class="col-2 col-md-2 col-sm-2"></div>
		<div class="col-4 col-sm-8 col-md-6 ">

			<div class="card">

				<div class="card-header bg-success">Quotation Details</div>
				<div class="card-body">

					<form action="readMailDetails" method="post" autocomplete="off"
						class="justify-content-center">

						<div class="text-center">
							<button class="btn btn-primary">Click to read mail</button>
							<button class="btn btn-danger">Cancel</button>
						</div>



					</form>

				</div>

			</div>
		</div>
	</div>



</body>
</html>