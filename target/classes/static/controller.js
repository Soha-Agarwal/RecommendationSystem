/**
 * 
 */
var app = angular.module('app', []);
app.controller('postcontroller2', function($scope, $http, $location) {
	$scope.submitForm = function(){
		var url = "postinput";
		
		var config = {
                headers : {
                    'Accept':'application/json'
                }
        }
		var data = {
            amount: $scope.firstname,
            risk: $scope.lastname
        };
		
		$http.post(url, data, config).then(function (response) {
			$scope.response = response.data;
			$scope.response1="";
		
		}, function error(response) {
			$scope.postResultMessage = "Error with status: " +  response.statusText;
		});
		
		$scope.firstname = "";
		$scope.lastname = "";
	}
	
	$scope.getHistory = function(){
		var url = "getHistory";
		
		var config = {
                headers : {
                    'Accept':'application/json'
                }
        }
		
		
		$http.get(url).then(function (response) {
			$scope.response1 = response.data;
		}, function error(response) {
			$scope.postResultMessage = "Error with status: " +  response.statusText;
		});
		
		
	}
	$scope.saveData=function(record){
		var url="saveData";
		var config={
				headers : {
                    'Accept':'text/plain'
                }
		}
		var data = {
	            company: record.company,
	            price: record.price
	        };
		
		$http.post(url, data, config).then(function (response) {
			$scope.saveDataMessage = " Your Data for '"+data.company+"' has been Saved";
			console.log(data);
		}, function error(response) {
			$scope.saveDatamessage = "Error with status: " +  response.statusText;
		});
	}
});
 
app.controller('getcontroller', function($scope, $http, $location) {
	$scope.getfunction = function(){
		var url =  "getallcustomer";
		
		$http.get(url).then(function (response) {
			$scope.response = response.data
		}, function error(response) {
			$scope.postResultMessage = "Error with status: " +  response.statusText;
		});
	}
});

app.controller('logincontroller', function($scope, $http, $location) {
	$scope.submitForm = function(){
		var url = "postlogin";
		
		var config = {
                headers : {
                    'Content Type': 'application/json',
                    'Accept':'text/plain'
                }
        }
		var data = {
            username: $scope.username,
            password: $scope.password
        };
		
		$http.post(url, data, config).then(success(function(data) {
			$location.path("/dashboard.html");
		},
		function error(response) {
			$location.path("/hello.html");

			}));
		
		$scope.firstname = "";
		$scope.lastname = "";
	}
});
 