

var app1 = angular.module('loginapp', []);
app.controller('logincontroller', function($scope, $http, $location) {
	$scope.Recommend = function(){
		var url = $location.absUrl() + "Recommend";
		
		var config = {
                headers : {
                    'Accept': 'text/plain'
                }
        }
		var data = {
				amount:$scope.amount,
				risk: $scope.risk
        };
		
		$http.post(url, data, config).then(function (response) {
			$scope.postResultMessage = response.data;
		}, function error(response) {
			$scope.postResultMessage = "Error with status: " +  response.statusText;
		});
		
		$scope.amount= "";
		$scope.risk= "";
	}
});