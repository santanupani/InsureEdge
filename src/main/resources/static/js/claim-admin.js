var claimAdmin = angular.module('claimAdmin', ['ngRoute', 'ngCookies']);


claimAdmin.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
                .when('/claim-requests', {
                    'templateUrl': '/html/claim-requests-list.html',
                    'controller': 'listClaimRequestCtrl'
                })
                .when('/reject-claims', {
                    'templateUrl': '/html/claim-reject-list.html',
                    'controller': 'listRejectClaimRequestCtrl'
                })
                .when('/approved-claims', {
                    'templateUrl': '/html/approved-claims.html',
                    'controller': 'listApprovedClaimRequestCtrl'
                })
                .when('/claim-requests/:claimNumber', {
                    'templateUrl': '/html/claim-requests.html',
                    'controller': 'claimRequestCtrl'
                })
                .when('/claim-requests/approved/:claimNumber', {
                    'templateUrl': '/html/approved-claim.html',
                    'controller': 'approvedclaim'
                }).otherwise({
                    redirectTo: '/claim-requests'
        });
    }]);

claimAdmin.directive('modal', function () {
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

claimAdmin.controller('claimCtrl', function ($scope, $rootScope, $http, $cookies, $window) {

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

            if ($rootScope.session.role != "ClAIMADMIN") {
                $window.location.href = "/logout";
            }

            $http.defaults.headers.common['Authorization'] = 'Basic ' + $cookies.token;
        }).error(function (error, status) {
            $window.location.href = "/logout";
        });
    };

});


claimAdmin.controller('listClaimRequestCtrl', function ($scope, $rootScope, $http, $filter) {

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

claimAdmin.controller('listRejectClaimRequestCtrl', function ($scope, $rootScope, $http, $filter) {

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
                console.log(' all reejct claims retrived sucessfully');
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

claimAdmin.controller('listApprovedClaimRequestCtrl', function ($scope, $rootScope, $http, $filter) {

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
                console.log(' all approved claims retrived sucessfully');
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

claimAdmin.controller('claimRequestCtrl', function ($scope, $rootScope, $http, $routeParams, $sce, $location) {

    $scope.documentName;
    $scope.mode;
    $scope.decline;
    $scope.release;
    $scope.approve;
    $scope.init = function () {

        $scope.decline = {};
        $scope.approve = {};
        $scope.release = {};
        $scope.claimNumber = $routeParams.claimNumber;
        $scope.showModal = false;
        $scope.getAllClaimRequest();
        $scope.releaseFormInfo();
        $scope.isReleaseFormCreated = true;
       
      

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
                    //$scope.init();
                    $rootScope.message = "Claim Request Declined Successfully";
                    //$scope.mode = undefined;
                    $location.path("/claim-requests");
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


    $scope.releaseFormInfo = function () {
        angular.forEach($scope.questionnairres, function (questionnairre) {
            if (angular.equals(questionnairre.question, 'Value of Cash Claimed under the policy?')) {
                console.log('Question:' + questionnairre.question + ', answer is: ' + questionnairre.answer);
                $scope.amountClaim = questionnairre.answer;
            } else if (angular.equals(questionnairre.question, 'Event Date and Time')) {
                console.log('Question:' + questionnairre.question + ', answer is: ' + questionnairre.answer);
                $scope.lossDate = questionnairre.answer;
            }
        });
    };

    $scope.save = function (form) {

        if (form.$invalid) {
            console.log("Form Validation Failure");
        } else {
        	
            $scope.release.claimNumber = $scope.claimRequest.claimNumber;           
            $scope.release.policyNumber=$scope.claimRequest.policy.reference;
            $scope.release.insured = $scope.claimRequest.policy.client.clientName;            
            $scope.release.amountClaim = $scope.amountClaim;
            $scope.release.lessExcess = $scope.lessExcess;
            $scope.release.totalPayeble = $scope.totalPayable;           
            $scope.release.goodDescription = $scope.claimRequest.claimQuestionnaire[6].answer;            
            $scope.release.lossDate = $scope.claimRequest.claimQuestionnaire[17].answer;            
            $scope.release.lossDescription = $scope.claimRequest.claimQuestionnaire[6].answer;
           
            
            console.log($scope.release);
            $http({
                url: '/api/releaseForm',
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: $scope.release
            }).success(function (data, status) {
                console.log('get success code :' + status);
                if (status === 200) {
                    console.log('All the data are saved  for release form');
                    $rootScope.message = "Quotation Request Accepted Successfully";
                    $scope.isReleaseFormCreated = false;
                    $scope.viewreleaseFormPDF($scope.claimRequest.claimNumber);
                } else {
                    console.log('status:' + status);
                }
            }).error(function (error) {
                $rootScope.message = "Oops, we received your request, but there was an error processing it";
            });
        }
    };
    
    $scope.viewreleaseFormPDF = function (claimNumber) {
        console.log('Ref: ' + claimNumber);
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
                $scope.questionnairres = $scope.claimRequest.claimQuestionnaire;
                $scope.getClientClaims($scope.claimRequest.policy.reference);
                $scope.releaseFormInfo();

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
            url: '/api/claim-requests/' + claimNumber + "/request-approval",
            method: 'put',
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data, status) {
            console.log('get success code:' + status);
            if (status == 200) {
                console.log('Claim Approval Requested');
                $rootScope.message = "Claim Approval Requested Successfully";
                $location.path("/claim-requests");
            } else {
                console.log('status:' + status);
            }
        })
                .error(function (error) {
                    $rootScope.message = "";
                    console.log(error);
                });


    };
    $scope.requestForPendingDocuments = function (claimNumber) {

        $http({
            url: '/api/claim-requests/' + claimNumber + "/pendingDocuments",
            method: 'put',
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data, status) {
            console.log('get success code:' + status);
            if (status == 200) {
                console.log('Requested For Pending Documents');
                $rootScope.message = "Request for pending documents Successfull";
                $location.path("/claim-requests");
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
        saveAs(attachment, claimNumber + " - " + documentName);
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

claimAdmin.controller('approvedclaim', function ($scope, $rootScope, $http, $routeParams, $sce, $location) {

    $scope.documentName;
    $scope.mode;
    $scope.decline;
    $scope.release;
    $scope.approve;
    $scope.init = function () {

        $scope.decline = {};
        $scope.approve = {};
        $scope.release = {};
        $scope.claimNumber = $routeParams.claimNumber;
        $scope.showModal = false;
        $scope.getAllClaimRequest();
        $scope.releaseFormInfo();
        $scope.viewreleaseFormPDF($scope.claimNumber);
        $scope.getAllReleaseFormData($scope.claimNumber);
        $scope.isReleaseFormCreated = true;
       
      

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

    
    


    $scope.releaseFormInfo = function () {
        angular.forEach($scope.questionnairres, function (questionnairre) {
            if (angular.equals(questionnairre.question, 'Value of Cash Claimed under the policy?')) {
                console.log('Question:' + questionnairre.question + ', answer is: ' + questionnairre.answer);
                $scope.amountClaim = questionnairre.answer;
            } else if (angular.equals(questionnairre.question, 'Event Date and Time')) {
                console.log('Question:' + questionnairre.question + ', answer is: ' + questionnairre.answer);
                $scope.lossDate = questionnairre.answer;
            }
        });
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
        console.log('Ref: ' + claimNumber);
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
                $scope.questionnairres = $scope.claimRequest.claimQuestionnaire;
                $scope.getClientClaims($scope.claimRequest.policy.reference);
                $scope.releaseFormInfo();

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


  
    

    $scope.download = function (attachment, claimNumber, documentName) {
        saveAs(attachment, claimNumber + " - " + documentName);
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

