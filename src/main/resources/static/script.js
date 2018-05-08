	// create the module and name it scotchApp

/*var app = angular.module('app', []);
app.controller('postcontroller1', function($scope, $http, $location) {
	$scope.submitForm = function(){
		var url = $location.absUrl() + "postcustomer";
		
		var config = {
                headers : {
                    'Accept': 'text/plain'
                }
        }
		var data = {
            firstname: $scope.firstname,
            lastname: $scope.lastname
        };
		
		$http.post(url, data, config).then(function (response) {
			$scope.postResultMessage = response.data;
		}, function error(response) {
			$scope.postResultMessage = "Error with status: " +  response.statusText;
		});
		
		$scope.firstname = "";
		$scope.lastname = "";
	}
});*/
 

	var scotchApp = angular.module('scotchApp', ['ngRoute']);
	scotchApp.controller('postcontroller',function($scope,$location,$rootScope){
			$scope.submit=function(){
				var uname=$scope.username;
				var password=$scope.password;
				if($scope.username=='admin' && $scope.password=='admin')
					{
					$rootScope.loggedIn=true;
					$location.path('/dashboard');
					}
				else{
					alert("Wrong Stuff");
				}

			}
	});
	
	// configure our routes
	scotchApp.config(function($routeProvider) {
		$routeProvider

			// route for the home page
			.when('/', {
				templateUrl : 'pages/home.html',
				
			})
			
			.when('/view', {
				templateUrl : 'pages/ViewCurrent.html',
				controller  : 'mainController'			})

			// route for the about page
			.when('/about', {
				templateUrl : 'pages/about.html',
				controller  : 'aboutController'
			})
			.when('/dashboard', {
				resolve:{
					"check":function($location,$rootScope)
					{
					if(!$rootScope.loggedIn)
						{
						$location.path('/');
						}
				
					}
			
				},
			templateUrl : 'pages/dashboard.html',
			
			})
		
			// route for the contact page
			.when('/contact', {
				templateUrl : 'pages/contact.html',
				controller  : 'contactController'
			})
			.when('/register', {
                controller: 'RegisterController',
                templateUrl: 'pages/register.html',
                controllerAs: 'vm'
            })

            .otherwise({ redirectTo: '/login' });
			
	});

	// create the controller and inject Angular's $scope
	scotchApp.controller('mainController',function($scope, $http, $location) {
		$scope.viewstocks = function(){
			var url =  "getallstocks";
			
			$http.get(url).then(function (response) {
				$scope.response = response.data
				console.log("working");
			}, function error(response) {
				$scope.postResultMessage = "Error with status: " +  response.statusText;
			});
		}
	});


	scotchApp.controller('aboutController', function($scope) {
		$scope.message = 'Look! I am an about page.';
	});

	scotchApp.controller('contactController', function($scope) {
		$scope.message = 'Contact us! JK. This is just a demo.';
	});
		scotchApp.controller('RegisterController', function($scope) {
		$scope.message = 'Register!!!';
	});
		
		scotchApp.controller('viewController', function($scope) {
			$scope.message = 'Register!!!';
			$scope.viewstocks=function($scope){
				$scope.message="Hsabdjsadb";
			}
		});
		
		
		
		scotchApp.controller('logincontroller', function($scope, $http, $location) {
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
		 