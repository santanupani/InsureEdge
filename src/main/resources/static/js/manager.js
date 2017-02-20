var manager = angular.module('manager', ['ngRoute', 'ngCookies']);


manager.config(['$routeProvider', function ($routeProvider) {
	$routeProvider
	.when('/policy/approvals', {
		'templateUrl': '/html/policy-approvals.html',
		'controller': 'policyApprovalCtrl'
	}).when('/policy/:reference/approval', {
		'templateUrl': '/html/policy-approval.html',
		'controller': 'policyApprovalCtrl'
	}).when('/approved-claim-requests', {
		'templateUrl': '/html/approved-claims.html',
		'controller': 'listClaimRequestCtrl'
	}).when('/claim-requests/:claimNumber', {
		'templateUrl': '/html/accept-claim-requests.html',
		'controller': 'claimRequestCtrl'
	}).otherwise({
		redirectTo: '/policy/approvals'
	});
}]);

manager.directive('modal', function () {
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


manager.controller('managerLoginCtrl', function ($scope, $rootScope, $http, $cookies, $window) {

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

			if ($rootScope.session.role != "MANAGER") {
				$window.location.href = "/logout";
			}

			$http.defaults.headers.common['Authorization'] = 'Basic ' + $cookies.token;
		}).error(function (error, status) {
			$window.location.href = "/logout";
		});
	};

});

manager.controller('policyApprovalCtrl', function ($scope, $rootScope, $http, $routeParams, $location, $sce, $filter, $window, $cookieStore) {
	$rootScope.policies;
	
	$scope.init = function () {
		$scope.getPolicies();
	};

	$scope.updatePolicy = function (reference) {
		console.log('Update Policy');
		console.log('Policy Fee ' + $scope.policy.policyFee);
		$http({
			url: '/api/policy-update/' + reference,
			method: 'put',
			headers: {
				'Content-Type': 'application/json'
			},
			data: $scope.policy
		}).success(function (data, status) {
			console.log('get success code:' + status);
			if (status == 200) {
				$rootScope.message = "Policy Updated Successfully";
				$scope.policy = data;
				console.log('Policy Updated');
				$window.location.reload();
			} else {
				console.log('status:' + status);
			}
		})
		.error(function (error) {
			$rootScope.message = "Oops we recieved your request but there was a problem processing it";
			console.log(error);
		});
	};
	

	$scope.approvePolicy = function (reference) {
		console.log('Update Policy');
		console.log('Policy Fee ' + $scope.policy.policyFee);
		$http({
			url: '/api/policy/' + reference+'/approval',
			method: 'put',
			headers: {
				'Content-Type': 'application/json'
			},
			data: $scope.policy
		}).success(function (data, status) {
			console.log('get success code:' + status);
			if (status == 200) {
				$rootScope.message = "Policy Approved Successfully";
				$scope.policy = data;
				$location.path('/policy/approvals');
			} else {
				console.log('status:' + status);
			}
		})
		.error(function (error) {
			$rootScope.message = "Oops we recieved your request but there was a problem processing it";
			console.log(error);
		});
	};
	
	$scope.submitForPolicySchedule = function (reference) {
		console.log('Ref: ' + reference);
		$http({
			url: '/api/policy-schedules/' + reference,
			responseType: 'arraybuffer',
			headers: {'Accept': 'application/pdf'},
			method: 'get'
		}).success(function (data, status) {
			if (status == 200) {
				console.log('Retrieving policy schedule PDF');
				var file = new Blob([data], {type: 'application/pdf'});
				var fileURL = URL.createObjectURL(file);
				$scope.isPolicyCreated = true;
				$scope.policyScheduleContent = $sce.trustAsResourceUrl(fileURL);
			} else {
				console.log('status:' + status);
				$scope.error = "error status code : " + status;
			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};
			
	$scope.update = function (isUpdated) {
		$scope.isUpdate = isUpdated;
	};

	$scope.cancel = function (isUpdated) {
		$window.location.reload();
	};


	$scope.getPolicy = function (reference) {
		angular.forEach($rootScope.policies, function (policy) {
			if (angular.equals(reference, policy.reference)) {
				$scope.policy = policy;
				$scope.update(true);

				$scope.submitForPolicySchedule(reference);
				$location.path('/policy/' + reference+'/approval');
			}
		});
	};


	$scope.getPolicies = function () {
		$http({
			url: '/api/policies',
			method: 'get'
		}).success(function (data, status) {
			if (status == 200) {
				console.log('All policies retrived sucessfully');
				$rootScope.pendingApprovalPolicies = data;
				console.log($rootScope.pendingApprovalPolicies);
				$rootScope.policies = [];
				angular.forEach($rootScope.pendingApprovalPolicies,function(policy){
					
					if(angular.equals(policy.status,'Pending Approval')){
						console.log(policy.status);
						$rootScope.policies.push(policy);
					}
				});
				console.log($rootScope.policies);
				$scope.getPolicy($routeParams.reference);
			} else {
				console.log('status:' + status);
				$rootScope.error = "error status code : " + status;
			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};
	
	$scope.getClient = function () {
		angular.forEach($rootScope.policies, function (policy) {
			if (angular.equals($routeParams.reference, policy.reference)) {
				$scope.policy = policy;
				$scope.policy.client = policy.client;
				console.log('Client UI policies size: ' + $rootScope.policies.length);
				console.log('Client :' + $scope.policy.client);
				$location.path('/client/' + $routeParams.reference);
			}
		});
	};
});

manager.controller('listClaimRequestCtrl', function ($scope, $rootScope, $http, $filter) {

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

manager.controller('claimRequestCtrl', function ($scope, $rootScope, $http, $routeParams, $sce, $location) {

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
			url: '/api/claim-requests/' + claimNumber + "/accept",
			method: 'put',
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function (data, status) {
			console.log('get success code:' + status);
			if (status == 200) {
				console.log('Claim accepted');
				$rootScope.message = "Claim Request accepted Successfully";
				$location.path("/approved-claim-requests");
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