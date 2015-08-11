var broker = angular.module('broker', ['ngRoute']);

broker.config(['$routeProvider', function ($routeProvider) {
	$routeProvider
	.when('/quotation-requests/:reference', {
		'templateUrl': '/html/quotation-requests.html',
		'controller': 'quotationRequestsCtrl'
	}).when('/quotations', {
		'templateUrl': '/html/broker-scheduler.html',
		'controller': 'brokerSchedulerCtrl'
	}).otherwise({
		redirectTo: '/products'
	});
}]);

broker.controller('quotationRequestsCtrl', function ($scope, $routeParams, $http, $location, $rootScope) {

	$scope.reference ;
	$scope.quotationRequest;
	$scope.quotation ;
	$scope.mode ;
	$scope.reject;
	$scope.com ;


	$scope.init = function () {
		$scope.reference = $routeParams.reference;     
		$scope.reject = {};
		$scope.quotation = {
				"options" :[{"name": "Category 1"}]
		};
		$scope.getQuotationRequest($scope.reference);
		$scope.com = ['Cash', 'Bullion', 'Diamond' ,'Art'];
		$scope.cov = ['Cash And Valuables in Transit', 'Fine Art and Collectables', 'Static Cover Cash And Valuables' ,'Static And In Transit Cover Cash and Valuables'];
	};

	$scope.getQuotationRequest = function () {
		$http({
			url: '/api/quotation-requests/' + $scope.reference,
			method: 'get'
		}).
		success(function (data, status) {
			console.log('get success code::' + status);
			if (status == 200) {
				$scope.quotationRequest = data;
				console.log('Quotationdetail Detail::' + $scope.quotationRequest);
			} else {
				console.log('status:' + status);
			}
		})
		.error(function (error) {
			console.log(error);
		});
	};

	$scope.rejectQuotationRequest = function (rejectform) {
		if (rejectform.$invalid) {
			console.log("Form Validation Failure");
		} else {
			$scope.reject.status = "REJECTED";
			$http({
				url: '/api/quotation-requests/' + $scope.reference + "/reject",
				method: 'put',
				headers: {
					'Content-Type': 'application/json'
				},
				data: $scope.reject
			}). success(function (data, status) {
				console.log('get success code:' + status);
				if (status == 200) {
					console.log('Quotation Rejected. Reason:' + $scope.reject.reason);
					$scope.init();
					$rootScope.message = "Quotation Request Rejected Successfully";
					$scope.mode = undefined;
				} else {
					console.log('status:' + status);
				}
			})
			.error(function (error) {
				$rootScope.message = "";
				console.log(error);
			});
		}
	};

	$scope.changeMode = function(mode){
		$scope.mode = mode;
	}; 

	$scope.add = function () {
		var option = {};
		var optionName = "Category " + ($scope.quotation.options.length + 1);
		option.name = optionName;
		$scope.quotation.options.push(option);

	};

	$scope.remove = function(){
		if($scope.quotation.options.length>1)
			$scope.quotation.options.pop();

	};

	$scope.save=function(form){

		if (form.$invalid) {
			console.log("Form Validation Failure");
		} else {
			$scope.quotation.reference = $scope.reference;

			console.log("Ref Test : "+$scope.reference);
			console.log($scope.quotationRequest);
			console.log($scope.quotation);                           
			$http({
				url: '/api/quotations',
				method: 'post',
				headers: {
					'Content-Type': 'application/json'
				},
				data: $scope.quotation
			}).success(function (data, status) {
				console.log('get success CODE:' + status);
				if (status === 200) {
					console.log('All the data are saved in quotationOptions and quotation table');
					$scope.init();
					$rootScope.message = "Quotation Request Accepted Successfully";
					$scope.mode = undefined;
				} else {
					console.log('status:' + status);
				}
			}).error(function (error) {
				$rootScope.message = "Oops, we received your request, but there was an error processing it";
			}); 
		}
	};


});



broker.controller('brokerSchedulerCtrl', function ($scope, $rootScope, $http,$filter) {

	$scope.mode ;
	$scope.noOfDays=[];
	$scope.quotations = [];
	$scope.currDate; 

	$scope.init = function () {
		$scope.currDate = $filter("date")(Date.now(),'dd/MM/yyyy');
		$scope.getAllQuotations();
	};


	$scope.getAllQuotations = function () {
		console.log('get all quotations');
		$http({
			url: '/api/quotations',
			method: 'get'
		}).success(function (data, status) {
			if (status === 200) {
				console.log('retrived successfully');
				$scope.quotations = data;
				for (var i = 0; i < $scope.quotations.length; i++) {
					$scope.noOfDays[i] = $scope.dayDiff($scope.quotations[i].quotationRequest.createDate,$scope.currDate);
				}
			} else {
				console.log('status:' + status);
				$rootScope.error = "error status code : " + status;
				;
			}
		}).error(function (error) {
			$rootScope.message = "Oops, we received your request, but there was an error processing it";
		});
	};

	$scope.closeNotification = function () {
		$rootScope.message = undefined;
	};

	$scope.getQuotation = function (reference) {

		$http({
			url: '/api/quotations/' + reference,
			method: 'get'
		}).success(function (data, status) {
			if (status == 200) {
				console.log('quotations retrived sucessfully');
				$rootScope.quotation = data;
				$scope.init();
			} else {
				console.log('status:' + status);
				$rootScope.error = "error status code : " + status;

			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};

	$scope.changeMode = function(mode){
		$scope.mode = mode;
	};

	$scope.closeNotification = function () {
		$rootScope.message = undefined;
	};

	$scope.dayDiff = function(firstDate,secondDate){
		var date2 = new Date($scope.formatString(secondDate));
		var date1 = new Date($scope.formatString(firstDate));
		var timeDiff = Math.abs(date2.getTime() - date1.getTime());   
		$scope.dayDifference = Math.ceil(timeDiff / (1000 * 3600 * 24));
//		alert($scope.dayDifference);
		return $scope.dayDifference;
	}

	$scope.formatString = function(format) {
		var day   = parseInt(format.substring(0,2));
		var month  = parseInt(format.substring(3,5));
		var year   = parseInt(format.substring(6,10));
		var date = new Date(year, month-1, day);
		return date;
	}
});