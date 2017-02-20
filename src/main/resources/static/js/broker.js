var broker = angular.module('broker', ['ngRoute', 'ngCookies']);

broker.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
                .when('/quotation-requests', {
                    'templateUrl': '/html/quotation-requests-list.html',
                    'controller': 'listQuotationRequestCtrl'
                })
                .when('/approved-quotation-requests', {
                    'templateUrl': '/html/approved-quotations.html',
                    'controller': 'approvedQuotationRequestCtrl'
                })
                .when('/rejected-quotation-requests', {
                    'templateUrl': '/html/rejected-quotations.html',
                    'controller': 'rejectedQuotationRequestCtrl'
                })
                .when('/quotation-requests/:reference', {
                    'templateUrl': '/html/quotation-requests.html',
                    'controller': 'quotationRequestsCtrl'
                }).when('/quotations', {
                    'templateUrl': '/html/broker-scheduler.html',
                    'controller': 'brokerSchedulerCtrl'
                }).when('/claims', {
                    'templateUrl': '/html/approved-claims.html',
                    'controller': 'listClaimRequestCtrl'
                }).when('/claim-requests/:claimNumber', {
                    'templateUrl': '/html/approved-claim-requests.html',
                    'controller': 'claimRequestCtrl'
                }).otherwise({
                    redirectTo: '/quotation-requests'
                });
    }]);


broker.directive('modal', function () {
	return {
		template: '<div class="modal fade">' +
		'<div class="modal-dialog">' +
		'<div class="modal-content">' +
		'<div class="modal-header">' +
		'<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' +
		'<h4 class="modal-title">{{ documentName }}</h4>' +
		'</div>' +
		'<div class="modal-body" ng-transclude></div>' +
		'</div>' +
		'</div>' +
		'</div>',
		restrict: 'E',
		transclude: true,
		replace: true,
		scope: true,
		link: function postLink(scope, element, attrs) {
			scope.title = attrs.title;

			scope.$watch(attrs.visible, function (value) {
				if (value == true)
					$(element).modal('show');
				else
					$(element).modal('hide');
			});

			$(element).on('shown.bs.modal', function () {
				scope.$apply(function () {
					scope.$parent[attrs.visible] = true;
				});
			});

			$(element).on('hidden.bs.modal', function () {
				scope.$apply(function () {
					scope.$parent[attrs.visible] = false;
				});
			});
		}
	};
});

broker.directive('format', function ($filter) {
	'use strict';

	return {
		require: '?ngModel',
		link: function (scope, elem, attrs, ctrl) {
			if (!ctrl) {
				return;
			}

			ctrl.$formatters.unshift(function () {
				return $filter('number')(ctrl.$modelValue);
			});

			ctrl.$parsers.unshift(function (viewValue) {
				var plainNumber = viewValue.replace(/[\,\.]/g, ''),
				b = $filter('number')(plainNumber);

				elem.val(b);

				return plainNumber;
			});
		}
	};
});

broker.controller('brokerCtrl', function ($scope, $rootScope, $http, $cookies, $window) {


	$scope.init = function () {
		if ($cookies.token == undefined) {
			$window.location.href = "/login?state=" + encodeURIComponent($window.location.href);
		} else {
			$scope.validate($cookies.token);

		}
	};

	$scope.validate = function (token) {
		$http({
			method: 'POST',
			url: '/validate',
			headers: {
				"Content-Type": "text/html"
			},
			data: token
		}).success(function (data, status) {
			$rootScope.session = data;

			if ($rootScope.session.role != "BROKER") {
				$window.location.href = "/logout";
			}

			$http.defaults.headers.common['Authorization'] = 'Basic ' + $cookies.token;
		}).error(function (error, status) {
			$window.location.href = "/logout";
		});
	};

});

broker.controller('quotationRequestsCtrl', function ($scope, $routeParams, $http, $location, $rootScope, $sce) {

    $scope.reference;
    $scope.numberOfDecimals = 2;
    $scope.quotationRequest;
    $scope.quotation;
    $scope.mode;
    $scope.reject;
    $scope.com;
    $scope.questionnairres = [];
    $scope.updateQuotation;
    $scope.cover;
   
   
    



    $scope.init = function () {
        $scope.reference = $routeParams.reference;
        $scope.isQuotationCreated = true;
        $scope.reject = {};
        $scope.quotation = {
            "options": []
        };
        $scope.getQuotationRequest($scope.reference);

    };

    $scope.getQuotationRequest = function () {
        $http({
            url: '/api/quotation-requests/' + $scope.reference,
            method: 'get'
        }).
        		success(function (data, status) {
                    console.log('get success code1::' + status);
                    if (status == 200) {
                        $scope.quotationRequest = data; 
                        
                        $scope.questionnairres = $scope.quotationRequest.questionnaire;

                        for (var i = 0; i < $scope.quotationRequest.questionnaire.length; i++) {

                            if (angular.equals($scope.quotationRequest.questionnaire[i].answer, 'true')) {
                                $scope.quotationRequest.questionnaire[i].answer = 'Yes';
                                console.log($scope.quotationRequest.questionnaire[i].answer);
                            } else if (angular.equals($scope.quotationRequest.questionnaire[i].answer, 'false')) {
                                $scope.quotationRequest.questionnaire[i].answer = 'No';
                                console.log($scope.quotationRequest.questionnaire[i].answer);
                            }
                        }
                        
                        if($scope.quotationRequest.product.id != 5){
                        	console.log('I am here for Product 1-4');
                        	angular.forEach($scope.quotationRequest.locationOptions, function (locationOption) {
                            locationOption.cover = 'Fire, Accidental damage, Hijacking, Theft & Armed Robbery - as per standard policy wording.';
                            locationOption.excess = 'R Nil';
                        });
                        }else if($scope.quotationRequest.product.id == 5){
                        	$scope.cover = 'Fire, Accidental damage, Hijacking, Theft & Armed Robbery - as per standard policy wording.';                        	                       
                        	console.log('I am here for Product 5');
                        }
                        console.log('Quotation Request Detail::' + $scope.quotationRequest);
                        console.log('Questionairres Detail::' + $scope.questionnairres);
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
            }).success(function (data, status) {
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

    $scope.changeMode = function (mode) {
        $scope.mode = mode;
    };

    $scope.viewUpdateQuotationPDF = function (reference) {
        console.log('Ref: ' + reference);
        $http({
            url: '/api/quotation-request-pdf/' + reference,
            responseType: 'arraybuffer',
            headers: {'Accept': 'application/pdf'},
            method: 'get'
        }).success(function (data, status) {
            if (status == 200) {
            	
            	console.log('Retrieving Quotation PDF data1'+ data);
                console.log('Retrieving Quotation PDF1');
                var file = new Blob([data], {type: 'application/pdf'});
                var fileURL = URL.createObjectURL(file);
                $scope.quotationPDF = $sce.trustAsResourceUrl(fileURL);
            } else {
                console.log('status:' + status);
                $scope.error = "error status code : " + status;
            }
        }).error(function (error) {
            console.log(error);
            $rootScope.error = error;
        });

    };


    $scope.save = function (form) {

        if (form.$invalid) {
            console.log("Form Validation Failure");
        } else {
        	if($scope.quotationRequest.product.id !=5){
        	
        	console.log("I am here at Product 1-4")
        		
            $scope.quotation.reference = $scope.reference;
            $scope.quotation.note = $scope.quotationRequest.note;                   
           

            console.log("Ref Test : " + $scope.reference);
            for (var i = 0; i < $scope.quotationRequest.locationOptions.length; i++) {
                $scope.quotation.options[i] = {};
                $scope.quotation.options[i].cover = $scope.quotationRequest.locationOptions[i].cover;
                $scope.quotation.options[i].commodity = $scope.quotationRequest.locationOptions[i].commodity;
                $scope.quotation.options[i].location = $scope.quotationRequest.locationOptions[i].fromLocation;
                $scope.quotation.options[i].duration = $scope.quotationRequest.locationOptions[i].duration;
                $scope.quotation.options[i].excess = $scope.quotationRequest.locationOptions[i].excess;
                $scope.quotation.options[i].premium = $scope.quotationRequest.locationOptions[i].premium;
                $scope.quotation.options[i].limit = $scope.quotationRequest.locationOptions[i].limit;
                $scope.quotation.options[i].staticLimit = $scope.quotationRequest.locationOptions[i].staticLimit;
                if ($scope.quotationRequest.locationOptions[i].pavement == null) {
                    $scope.quotation.options[i].pavement = 0;
                } else {
                    $scope.quotation.options[i].pavement = $scope.quotationRequest.locationOptions[i].pavement;
                }

            }
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


                	
//					$scope.init();
					$rootScope.message = "Quotation Request Accepted Successfully";
//					$scope.mode = undefined;
					$scope.isQuotationCreated = false;
					$scope.viewQuotationPDF($scope.quotation.reference);

				} else {
					console.log('status:' + status);
				}
			}).error(function (error) {
				$rootScope.message = "Oops, we received your request, but there was an error processing it";
			});
        	
        	} else if($scope.quotationRequest.product.id ==5){
        		
        		
        		console.log("I am here in Product 5");
        		
        		$scope.quotation.reference = $scope.reference;                
                $scope.quotation.cover = $scope.cover;
                $scope.quotation.premium = $scope.quotationRequest.premium;
                
              
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

    					$rootScope.message = "Quotation Request Accepted Successfully";

    					$scope.isQuotationCreated = false;
    					$scope.viewQuotationPDF($scope.quotation.reference);

    				} else {
    					console.log('status:' + status);
    				}
    			}).error(function (error) {
    				$rootScope.message = "Oops, we received your request, but there was an error processing it";
    			});
        	}
        
        }
      
	};


	$scope.updateQuotation = function () {
		$scope.updateQuotation = {};
		$scope.updateQuotation.reference = $scope.quotationRequest.reference;
		console.log('Updated quotation reference: '+$scope.updateQuotation.reference);
		$scope.updateQuotation.options = [];
		$scope.updateQuotation.options = $scope.quotationRequest.locationOptions;
		for(var i=0;i<$scope.quotationRequest.locationOptions.length;i++){
//			$scope.updateQuotation.options[i] = {};
			$scope.updateQuotation.options[i].pavement = $scope.quotationRequest.locationOptions[i].pavement;
			$scope.updateQuotation.options[i].commodity = $scope.quotationRequest.locationOptions[i].commodity;
			$scope.updateQuotation.options[i].staticLimit = $scope.quotationRequest.locationOptions[i].staticLimit;
			$scope.updateQuotation.options[i].duration = $scope.quotationRequest.locationOptions[i].duration;
			$scope.updateQuotation.options[i].cover = $scope.quotationRequest.locationOptions[i].cover;
			$scope.updateQuotation.options[i].excess = $scope.quotationRequest.locationOptions[i].excess;
			$scope.updateQuotation.options[i].limit = $scope.quotationRequest.locationOptions[i].limit;
			$scope.updateQuotation.options[i].location = $scope.quotationRequest.locationOptions[i].fromLocation;
			$scope.updateQuotation.options[i].premium = $scope.quotationRequest.locationOptions[i].premium;
		}

		$http({
			url: '/api/quotation-update/'+$scope.updateQuotation.reference,
			method: 'put',
			headers: {
				'Content-Type': 'application/json'
			},
			data: $scope.updateQuotation
		}).success(function (data, status) {
			console.log('get success code:' + status);
			if (status == 200) {
				$rootScope.message = "Quotation updated Successfully";
				$scope.viewUpdateQuotationPDF($scope.updateQuotation.reference);
			} else {
				console.log('status:' + status);
			}
		})
		.error(function (error) {
			$rootScope.message = "Oops we recieved your request but there was a problem processing it";
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
			}).success(function (data, status) {
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

	$scope.changeMode = function (mode) {
		$scope.mode = mode;
	};

	
	//Call for create new Quotation
	$scope.viewQuotationPDF = function (reference) {
		console.log('Ref: ' + reference);
		$http({
			url: '/api/quotation-request-pdf/' + reference,
			responseType: 'arraybuffer',
			headers: {'Accept': 'application/pdf'},
			method: 'get'
		}).success(function (data, status) {
			
			if (status == 200) {
				
				console.log('Retrieving Quotation PDF');
				var file = new Blob([data], {type: 'application/pdf'});
				var fileURL = URL.createObjectURL(file);
				$scope.quotationPDF = $sce.trustAsResourceUrl(fileURL);
			} else {
				console.log('status:' + status);
				$scope.error = "error status code : " + status;
			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});

	};


	$scope.submitQuotation = function () {

		$http({
			url: '/api/quotation-submit/' + $scope.reference,
			method: 'post',
			headers: {
				'Content-Type': 'application/json'
			},
			data: $scope.reference
		}).success(function (data, status) {
			console.log('Submit quotation status :' + status);
			if (status === 200) {
				console.log('Email notification send succesfully to the client');
				$scope.init();
				$scope.quotationView = true;
				$scope.mode = undefined;
				$rootScope.message = "Quotation Accepted Successfully";
			} else {
				console.log('status:' + status);
			}
		}).error(function (error) {
			$rootScope.message = "Oops, we received your request, but there was an error processing it";
		});
	};

	$scope.formatNumber = function (nmbr) {
		return $filter('currency')(nmbr, 'R ', 2);

	};
});


broker.controller('listQuotationRequestCtrl', function ($scope, $rootScope, $http, $filter) {


	$scope.init = function () {
		$scope.getAllQuotations();
	};

	$scope.getAllQuotations = function () {
		console.log('get all quotations');
		$http({
			url: '/api/quotation-requests',
			method: 'get'
		}).success(function (data, status) {
			if (status === 200) {
				console.log('retrived successfully');
				$scope.quotationRequests = data;
//				if($scope.quotationRequests.status == "APPLIED"){
//				$scope.newQuotationRequests = $scope.quotationRequests;
//				}
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


});

broker.controller('rejectedQuotationRequestCtrl', function ($scope, $rootScope, $http, $filter) {


	$scope.init = function () {
		$scope.getAllQuotations();
	};

	$scope.getAllQuotations = function () {
		console.log('get all quotations');
		$http({
			url: '/api/quotation-requests',
			method: 'get'
		}).success(function (data, status) {
			if (status === 200) {
				console.log('retrived successfully');
				$scope.quotationRequests = data;
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


});

broker.controller('approvedQuotationRequestCtrl', function ($scope, $rootScope, $http, $filter) {


	$scope.init = function () {
		$scope.getAllQuotations();
	};

	$scope.getAllQuotations = function () {
		console.log('get all quotations');
		$http({
			url: '/api/quotation-requests',
			method: 'get'
		}).success(function (data, status) {
			if (status === 200) {
				console.log('retrived successfully');
				$scope.quotationRequests = data;
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


	$scope.closeNotification = function () {
		$rootScope.message = undefined;
	};


});

broker.controller('brokerSchedulerCtrl', function ($scope, $rootScope, $http, $filter) {

	$scope.mode;
	$scope.noOfDays = [];
	$scope.quotations = [];
	$scope.currDate;

	$scope.init = function () {
		$scope.currDate = $filter("date")(Date.now(), 'dd/MM/yyyy');
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
					$scope.noOfDays[i] = $scope.dayDiff($scope.quotations[i].quotationRequest.createDate, $scope.currDate);
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
		angular.forEach($scope.quotations, function (quotation) {
			if (quotation.quotationRequest.reference == referennce) {
				return quotation;
			}
		});
	};

	$scope.changeMode = function (mode) {
		$scope.mode = mode;
	};

	$scope.closeNotification = function () {
		$rootScope.message = undefined;
	};

	$scope.dayDiff = function (firstDate, secondDate) {
		var date2 = new Date($scope.formatString(secondDate));
		var date1 = new Date($scope.formatString(firstDate));
		var timeDiff = Math.abs(date2.getTime() - date1.getTime());
		$scope.dayDifference = Math.ceil(timeDiff / (1000 * 3600 * 24));
		return $scope.dayDifference;
	};

	$scope.formatString = function (format) {
		var day = parseInt(format.substring(0, 2));
		var month = parseInt(format.substring(3, 5));
		var year = parseInt(format.substring(6, 10));
		var date = new Date(year, month - 1, day);
		return date;
	};

});

broker.controller('listClaimRequestCtrl', function ($scope, $rootScope, $http, $filter) {

	$scope.init = function () {
		$scope.getAllClaimRequest();
	};

	$scope.getAllClaimRequest = function () {
		$scope.request = [];
		$http({
			url: '/api/claim-requests',
			method: 'get'
		}).success(function (data, status) {
			if (status == 200) {
				console.log(' all claim Request retrived sucessfully');
				$scope.claimRequest = data;

			} else {
				console.log('status:' + status);
				$rootScope.error = "error status code : " + status;
			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};
	
	

});

broker.controller('claimRequestCtrl', function ($scope, $rootScope, $http, $routeParams, $sce, $location) {

	$scope.documentName;
	$scope.mode;
	$scope.decline;	
	$scope.init = function () {
		$scope.decline = {};
		$scope.claimNumber = $routeParams.claimNumber;	
		$scope.showModal = false;
		$scope.getAllClaimRequest();
		$scope.getAllReleaseFormData($scope.claimNumber);
		$scope.viewreleaseFormPDF($scope.claimNumber);

	};

	$scope.toggleModal = function (attachment, documentName) {
		console.log(attachment);
		console.log(documentName);
		$scope.showModal = !$scope.showModal;
		$scope.attachment = attachment;
		$scope.documentName = documentName;
	};
	$scope.changeMode = function (mode) {
		$scope.mode = mode;
	};

	$scope.declineClaimRequest = function (declineform) {
		if (declineform.$invalid) {
			console.log("Form Validation Failure");
		} else {
			$scope.decline.status = "DECLINED";
			$http({
				url: '/api/claim-requests/' + $scope.claimNumber + "/decline",
				method: 'put',
				headers: {
					'Content-Type': 'application/json'
				},
				data: $scope.decline
			}).success(function (data, status) {
				console.log('get success code:' + status);
				if (status == 200) {
					console.log('Claim declined Reason:' + $scope.decline.reason);
					$scope.init();
					$rootScope.message = "Claim Request Declined Successfully";
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
	
	$scope.getAllReleaseFormData = function (claimNumber) {	
		$http({			
			url: '/api/getReleaseFormData/' + claimNumber,			
			method: 'get'
		}).success(function (data, status) {
			if (status == 200) {
				console.log('ReleaseForm Request retrived sucessfully');
				$rootScope.releaseForm = data;
				
			} else {
				console.log('status:' + status);
				$rootScope.error = "error status code : " + status;
			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};
	
	$scope.viewreleaseFormPDF = function (claimNumber) {
        console.log('ClaimNumber No: ' + claimNumber);
        $http({
            url: '/api/release-form-pdf/' + claimNumber,
            responseType: 'arraybuffer',
            headers: {'Accept': 'application/pdf'},
            method: 'get'
        }).success(function (data, status) {
            if (status == 200) {
                console.log('Retrieving ReleaseForm PDF');
                console.log('Retrieving Dada'+data );
                var file = new Blob([data], {type: 'application/pdf'});
                var fileURL = URL.createObjectURL(file);
                $scope.releaseFormPDF = $sce.trustAsResourceUrl(fileURL);
            } else {
                console.log('status:' + status);
                $scope.error = "error status code : " + status;
            }
        }).error(function (error) {
            console.log(error);
            $rootScope.error = error;
        });

    };
	
	
	$scope.getClaimRequest = function (claimNumber) {
		$http({
			url: '/api/claim-requests/' + claimNumber,
			method: 'get'
		}).success(function (data, status) {
			if (status == 200) {
				console.log('claim Request retrived sucessfully');
				$rootScope.claimRequest = data;
				$scope.getClientClaims($scope.claimRequest.policy.reference);

				if ($rootScope.claimRequest.investigationReportC != null) {
					$scope.getInvestigationReport($rootScope.claimRequest.claimNumber);
				}
				if ($rootScope.claimRequest.affidavitC != null) {
					$scope.getAffidavit($rootScope.claimRequest.claimNumber);
				}
				if ($rootScope.claimRequest.comfirmationAmountC != null) {
					$scope.getComfirmationAmount($rootScope.claimRequest.claimNumber);
				}
				if ($rootScope.claimRequest.proofOfPickupC != null) {
					$scope.getProofOfPickup($rootScope.claimRequest.claimNumber);
				}
				if ($rootScope.claimRequest.transTrackDocumentC != null) {
					$scope.getTransTrackDocument($rootScope.claimRequest.claimNumber);
				}
				if ($rootScope.claimRequest.reportC != null) {
					$scope.getQuote($rootScope.claimRequest.claimNumber);
				}
				if ($rootScope.claimRequest.proofOfPickupC != null) {
					$scope.getProofOfPickup($rootScope.claimRequest.claimNumber);
				}
				if ($rootScope.claimRequest.amountBankedC != null) {
					$scope.getAmountBanked($rootScope.claimRequest.claimNumber);
				}
				if ($rootScope.claimRequest.quoteC != null) {
					$scope.getQuote($rootScope.claimRequest.claimNumber);
				}
				if ($rootScope.claimRequest.photo1C != null) {
					$scope.getPhoto1($rootScope.claimRequest.claimNumber);
				}
				if ($rootScope.claimRequest.photo2C != null) {
					$scope.getPhoto2($rootScope.claimRequest.claimNumber);
				}
				if ($rootScope.claimRequest.photo3C != null) {
					$scope.getPhoto3($rootScope.claimRequest.claimNumber);
				}
				if ($rootScope.claimRequest.photo4C != null) {
					$scope.getPhoto4($rootScope.claimRequest.claimNumber);
				}

				console.log('Returned claim request: ' + data);
			} else {
				console.log('status:' + status);
				$rootScope.error = "error status code : " + status;
			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};

	$scope.getAllClaimRequest = function () {
		$scope.request = [];
		$http({
			url: '/api/claim-requests',
			method: 'get'
		}).success(function (data, status) {
			if (status == 200) {
				console.log(' all claim Request retrived sucessfully');
				$rootScope.claimRequests = data;
				$scope.getClaimRequest($routeParams.claimNumber);

			} else {
				console.log('status:' + status);
				$rootScope.error = "error status code : " + status;
			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};

	$scope.getClientClaims = function (reference) {
		$scope.clientClaims = [];
		console.log("policy : " + $rootScope.claimRequests);
		angular.forEach($rootScope.claimRequests, function (claims) {
			if (angular.equals(claims.policy.reference, reference)) {
				console.log("Claim obtained " + claims.policy.reference);
				$scope.clientClaims.push(claims);
				console.log($scope.clientClaims);
			} else {
				console.log("Not matched....");
			}
		});
	};


	$scope.approveClaim = function (claimNumber) {

		$http({
			url: '/api/claim-requests/' + claimNumber + "/approve",
			method: 'put',
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function (data, status) {
			console.log('get success code:' + status);
			if (status == 200) {
				console.log('Claim approved');
				$rootScope.message = "Claim Request approved Successfully";
				$location.path("/claims");
			} else {
				console.log('status:' + status);
			}
		})
		.error(function (error) {
			$rootScope.message = "";
			console.log(error);
		});


	};


	$scope.download = function (attachment, claimNumber, documentName) {
		saveAs(attachment, claimNumber +" - "+ documentName);
	};

	$scope.getInvestigationReport = function (claimNumber) {
		$http({
			url: '/api/claim/' + claimNumber + '/investigationReport',
			responseType: 'arraybuffer',
			headers: {'Accept': '*/*'},
			method: 'get'
		}).success(function (data, status) {
			console.log(data);
			if (status == 200) {
				console.log('Retrieving Inverstigation report');
				angular.forEach(data, function (file) {
					console.log(file.toString());
				});
				var investigationReport = new Blob([data], {type: $rootScope.claimRequest.investigationReportC.toString()});
				var fileURL = URL.createObjectURL(investigationReport);
				$scope.investigationReport = investigationReport;
				$scope.investigationReportName = "Investigation Report";
				$scope.investigationReportURL = $sce.trustAsResourceUrl(fileURL);
			} else {
				console.log('status:' + status);
				$scope.error = "error status code : " + status;
			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};
	$scope.getComfirmationAmount = function (claimNumber) {
		$http({
			url: '/api/claim/' + claimNumber + '/comfirmationAmount',
			responseType: 'arraybuffer',
			headers: {'Accept': '*/*'},
			method: 'get'
		}).success(function (data, status) {
			console.log(data);
			if (status == 200) {
				console.log('Retrieving comfirmationAmount');
				var comfirmationAmount = new Blob([data], {type: $rootScope.claimRequest.comfirmationAmountC.toString()});
				var fileURL = URL.createObjectURL(comfirmationAmount);
				$scope.comfirmationAmount = comfirmationAmount;
				$scope.comfirmationAmountName = "Comfirmation Amount";
				$scope.comfirmationAmountURL = $sce.trustAsResourceUrl(fileURL);
			} else {
				console.log('status:' + status);
				$scope.error = "error status code : " + status;
			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};
	$scope.getProofOfPickup = function (claimNumber) {
		$http({
			url: '/api/claim/' + claimNumber + '/proofOfPickup',
			responseType: 'arraybuffer',
			headers: {'Accept': '*/*'},
			method: 'get'
		}).success(function (data, status) {
			console.log(data);
			if (status == 200) {
				console.log('Retrieving proofOfPickup');
				var proofOfPickup = new Blob([data], {type: $rootScope.claimRequest.proofOfPickupC.toString()});
				var fileURL = URL.createObjectURL(proofOfPickup);
				$scope.proofOfPickup = proofOfPickup;
				$scope.proofOfPickupName = "Proof Of Pick Up";
				$scope.proofOfPickupURL = $sce.trustAsResourceUrl(fileURL);
			} else {
				console.log('status:' + status);
				$scope.error = "error status code : " + status;
			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};
	$scope.getAffidavit = function (claimNumber) {
		$http({
			url: '/api/claim/' + claimNumber + '/affidavit',
			responseType: 'arraybuffer',
			headers: {'Accept': '*/*'},
			method: 'get'
		}).success(function (data, status) {
			console.log(data);
			if (status == 200) {
				console.log('Retrieving affidavit');
				var affidavit = new Blob([data], {type: $rootScope.claimRequest.affidavitC.toString()});
				var fileURL = URL.createObjectURL(affidavit);
				$scope.affidavit = affidavit;
				$scope.affidavitName = "Affidavit";
				$scope.affidavitURL = $sce.trustAsResourceUrl(fileURL);
			} else {
				console.log('status:' + status);
				$scope.error = "error status code : " + status;
			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};
	$scope.getAmountBanked = function (claimNumber) {
		$http({
			url: '/api/claim/' + claimNumber + '/amountBanked',
			responseType: 'arraybuffer',
			headers: {'Accept': '*/*'},
			method: 'get'
		}).success(function (data, status) {
			console.log(data);
			if (status == 200) {
				console.log('Retrieving amountBanked');
				var amountBanked = new Blob([data], {type: $rootScope.claimRequest.amountBankedC.toString()});
				var fileURL = URL.createObjectURL(amountBanked);
				$scope.amountBanked = amountBanked;
				$scope.amountBankedName = "Amount Banked";
				$scope.amountBankedURL = $sce.trustAsResourceUrl(fileURL);
			} else {
				console.log('status:' + status);
				$scope.error = "error status code : " + status;
			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};
	$scope.getTransTrackDocument = function (claimNumber) {
		$http({
			url: '/api/claim/' + claimNumber + '/transTrackDocument',
			responseType: 'arraybuffer',
			headers: {'Accept': '*/*'},
			method: 'get'
		}).success(function (data, status) {
			console.log(data);
			if (status == 200) {
				console.log('Retrieving transTrackDocument');
				var transTrackDocument = new Blob([data], {type: $rootScope.claimRequest.transTrackDocumentC.toString()});
				var fileURL = URL.createObjectURL(transTrackDocument);
				$scope.transTrackDocument = transTrackDocument;
				$scope.transTrackDocumentName = "Trans Track Document";
				$scope.transTrackDocumentURL = $sce.trustAsResourceUrl(fileURL);
			} else {
				console.log('status:' + status);
				$scope.error = "error status code : " + status;
			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};
	$scope.getQuote = function (claimNumber) {
		$http({
			url: '/api/claim/' + claimNumber + '/quote',
			responseType: 'arraybuffer',
			headers: {'Accept': '*/*'},
			method: 'get'
		}).success(function (data, status) {
			console.log(data);
			if (status == 200) {
				console.log('Retrieving quote');
				var quote = new Blob([data], {type: $rootScope.claimRequest.quoteC.toString()});
				var fileURL = URL.createObjectURL(quote);
				$scope.quote = quote;
				$scope.quoteName = "Quote";
				$scope.quoteURL = $sce.trustAsResourceUrl(fileURL);
			} else {
				console.log('status:' + status);
				$scope.error = "error status code : " + status;
			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};
	$scope.getReport = function (claimNumber) {
		$http({
			url: '/api/claim/' + claimNumber + '/report',
			responseType: 'arraybuffer',
			headers: {'Accept': '*/*'},
			method: 'get'
		}).success(function (data, status) {
			console.log(data);
			if (status == 200) {
				console.log('Retrieving report');
				var report = new Blob([data], {type: $rootScope.claimRequest.affidavitC.toString()});
				var fileURL = URL.createObjectURL(report);
				$scope.report = report;
				$scope.reportName = "Report";
				$scope.reportURL = $sce.trustAsResourceUrl(fileURL);
			} else {
				console.log('status:' + status);
				$scope.error = "error status code : " + status;
			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};
	$scope.getPhoto1 = function (claimNumber) {
		$http({
			url: '/api/claim/' + claimNumber + '/photo1',
			responseType: 'arraybuffer',
			headers: {'Accept': '*/*'},
			method: 'get'
		}).success(function (data, status) {
			console.log(data);
			if (status == 200) {
				console.log('Retrieving photo1');
				var photo1 = new Blob([data], {type: $rootScope.claimRequest.photo1C.toString()});
				var fileURL = URL.createObjectURL(photo1);
				$scope.photo1 = photo1;
				$scope.photo1Name = "Photo 1";
				$scope.photo1URL = $sce.trustAsResourceUrl(fileURL);
			} else {
				console.log('status:' + status);
				$scope.error = "error status code : " + status;
			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};
	$scope.getPhoto2 = function (claimNumber) {
		$http({
			url: '/api/claim/' + claimNumber + '/photo2',
			responseType: 'arraybuffer',
			headers: {'Accept': '*/*'},
			method: 'get'
		}).success(function (data, status) {
			console.log(data);
			if (status == 200) {
				console.log('Retrieving photo2');
				var photo2 = new Blob([data], {type: $rootScope.claimRequest.photo2C.toString()});
				var fileURL = URL.createObjectURL(photo2);
				$scope.photo2 = photo2;
				$scope.photo2Name = "Photo 2";
				$scope.photo2URL = $sce.trustAsResourceUrl(fileURL);
			} else {
				console.log('status:' + status);
				$scope.error = "error status code : " + status;
			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};
	$scope.getPhoto3 = function (claimNumber) {
		$http({
			url: '/api/claim/' + claimNumber + '/photo3',
			responseType: 'arraybuffer',
			headers: {'Accept': '*/*'},
			method: 'get'
		}).success(function (data, status) {
			console.log(data);
			if (status == 200) {
				console.log('Retrieving photo 3');
				var photo3 = new Blob([data], {type: $rootScope.claimRequest.photo3C.toString()});
				var fileURL = URL.createObjectURL(photo3);
				$scope.photo3 = photo3;
				$scope.photo3Name = "Photo 3";
				$scope.photo3URL = $sce.trustAsResourceUrl(fileURL);
			} else {
				console.log('status:' + status);
				$scope.error = "error status code : " + status;
			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};
	$scope.getPhoto4 = function (claimNumber) {
		$http({
			url: '/api/claim/' + claimNumber + '/photo4',
			responseType: 'arraybuffer',
			headers: {'Accept': '*/*'},
			method: 'get'
		}).success(function (data, status) {
			console.log(data);
			if (status == 200) {
				console.log('Retrieving photo 4');
				var photo4 = new Blob([data], {type: $rootScope.claimRequest.photo4C.toString()});
				var fileURL = URL.createObjectURL(photo4);
				$scope.photo4 = photo4;
				$scope.photo4Name = "Photo 4";
				$scope.photo4URL = $sce.trustAsResourceUrl(fileURL);
			} else {
				console.log('status:' + status);
				$scope.error = "error status code : " + status;
			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};
});
