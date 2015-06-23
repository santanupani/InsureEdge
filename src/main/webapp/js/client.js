var polygon = angular.module('polygon', ['ngRoute']);

polygon.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/products', {
            'templateUrl': '/html/products.html',
            'controller': 'productsCtrl'
        }).when('/products/:id/questionnaires', {
            'templateUrl': '/html/questionnaires.html',
            'controller': 'questionnairesCtrl'
        })
        .otherwise({
        	redirectTo: '/products'
        });
}]);





polygon.controller('productsCtrl', function ($scope, $rootScope, $http) {
     
    $scope.init = function(){
        $scope.getProducts();
    };

    $scope.getProducts = function () {
        console.log('get products');
        $http({
            url: '/api/products',
            method: 'get'
        }).success(function (data, status) {
            if (status == 200) {
                console.log('retrived successfully');
                $rootScope.products = data;
            } else {
                console.log('status:' + status);
                $rootScope.error = "error status code : " + status;;
            }
        }).error(function (error) {
            console.log(error);
        	$rootScope.error = error;;
                
        });
    };
});


polygon.controller('questionnairesCtrl', function ($scope, $rootScope, $http, $routeParams) {
    
	
	$scope.questionnaires = [];
        $scope.modelData={};
	
	$scope.init = function(){
            console.log($rootScope.products);
            if($rootScope.products == undefined) {
                 $scope.getProducts();
            } else {
		$scope.product = $rootScope.products[$routeParams['id']-1];
		$scope.getQuestionnaires($routeParams['id']);
            }
	};
	
	$scope.getProducts = function () {
            console.log('get products');
            $http({
                url: '/api/products',
                method: 'get'
            }).success(function (data, status) {
                if (status == 200) {
                    console.log('retrived successfully');
                    $rootScope.products = data;
                    $scope.init();
                } else {
                    console.log('status:' + status);
                    $rootScope.error = "error status code : " + status;;
                }
            }).error(function (error) {
                console.log(error);
        	$rootScope.error = error;;
                
            });
    };
    
    
    
    $scope.getQuestionnaires = function(productId){
    	 $http({
             url: '/api/products/'+productId+'/questionnaires',
             method: 'get'
         }).success(function (data, status) {
             if (status == 200) {
                 console.log('retrived successfully');
                 $scope.questionnaires = data;
                 for(var i=0; i<$scope.questionnaires.length; i++){
                     if($scope.questionnaires[i].answerType == 'checkbox'){
                         $scope.questionnaires[i].answer = 'false' ;
                     }
                 }
                 $scope.getBrokers();
             } else {
                 console.log('status:' + status);
                 $rootScope.error = "error status code : " + status;;
             }
         }).error(function (error) {
             console.log(error);
         	 $rootScope.error = error;;
                 
         });
    };
  
    
    $scope.getBrokers = function (){
        $http({
             url: '/api/brokers',
             method: 'get'
         }).success(function (data, status) {
             if (status == 200) {
                 console.log('brokers retrived successfully');
                 $scope.brokers = data;
                 console.log($scope.brokers);
             } else {
                 console.log('status:' + status);
                 $rootScope.error = "error status code : " + status;;
             }
         }).error(function (error) {
            console.log(error);
            $rootScope.error = error;     
         });
        
    };
    
    
     
    $scope.submit = function(form){ 
       $scope.modelData.applicantName=$scope.applicantName;
       $scope.modelData.applicantEmail=$scope.applicantEmail;
       $scope.modelData.brokerId=$scope.brokerId;
       $scope.modelData.productId=$routeParams['id'];
       $scope.modelData.questionnaires=$scope.questionnaires;
        
     	if(form.$invalid){
             console.log("Form Validation Failure");
             alert('Form Validation Failure');
        } else {
        	console.log("Service Call Here");
                console.log($scope.questionnaires);
                
                $http({
            url: 'http://localhost:8080/api/quotation-request',
            method: 'post',
            headers: {
                'Content-Type': 'application/json',   
            },
            data: $scope.modelData
        }).success(function (data, status) {
                if (status == 200) {
                    console.log('I am here');
                } else {
                    console.log('status:' + status);
                }
            })
            .error(function (error) {
                console.log(error);
            });

        
        }
    };
       
}); 
