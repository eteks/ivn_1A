<%-- 
    Document   : index
    Created on : 12 Aug, 2019, 1:09:36 PM
    Author     : ets-poc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IVN</title>
    </head>
    <body ng-app="myApp" ng-controller="LoginController">
        <p>IVN Phase 1A</p>
        <!--        <button ng-show="showSubmit == true" type="submit" class="btn btn-primary" ng-mousedown='doSubmit=true' ng-click="createpdbversion('submit')" name="submit">Submit</button>-->
        <button type="submit" class="btn btn-primary" ng-click="createpdbversion('submit')" name="submit">PDB Submit</button>
        <button type="submit" class="btn btn-primary" ng-click="createVehicleVersion('submit')" name="submit">Vehicle Submit</button>
        <script src="js/lib/test/jquery.min.js"></script>
        <script src="js/lib/test/materialize.min.js"></script>
        <script src="js/lib/test/angular.min.js"></script>
        <script>
                    var app = angular.module('myApp', []);
                    app.controller("LoginController", function ($scope, $http, $window) {
//                    alert("entered controller");
                        $scope.createpdbversion = function (event)
                        {
                            var status = true;
                            var data = {};
                            data['pdbversion'] = {"vehicle_id": "1", "status": true};
                            data['pdbdata_list'] = [{"model_id": "1", "dfm_id": "1", "status": "y"}, {"model_id": "1", "dfm_id": "2", "status": "n"}, {"model_id": "1", "dfm_id": "3", "status": "o"}, {"model_id": "2", "dfm_id": "1", "status": "o"}, {"model_id": "2", "dfm_id": "2", "status": "n"}, {"model_id": "2", "dfm_id": "3", "status": "y"}, {"model_id": "3", "dfm_id": "1", "status": "y"}, {"model_id": "3", "dfm_id": "2", "status": "n"}, {"model_id": "3", "dfm_id": "3", "status": "o"}];
                            data['button_type'] = event;
                            alert(JSON.stringify(data));
                            $http({
                                url: 'createpdbversion',
                                method: "POST",
                                data: data,
                            }).then(function (data, status, headers, config) {
//                                    alert(JSON.stringify(data));
//                                      alert(JSON.stringify(data.data.maps.status).slice(1, -1));
//                                      $window.open("pdb_listing.action","_self"); //                alert(data.maps);
//            //                        Materialize.toast(data['maps']["status"], 4000);
                            });
                        }
                        $scope.createVehicleVersion = function (event)
                        {
                            var status = true;
                            var data = {};
                            data['vehicle_version'] = {"vehiclename": "scorpio", "modelname": ["m1", "m2", "m3"]};
                            data['vehicle_list'] = {"vehicle_id": "1", "models": [{"model_id": 1, "model_name": "m1"}, {"model_id": 2, "model_name": "m2"}, {"model_id": 3, "model_name": "m3"}]};
                            data['button_type'] = event;
                            alert(JSON.stringify(data));
                            $http({
                                url: 'createVehicleVersion',
                                headers: {
                                    'Content-Type': 'application/json'
                                },
                                method: "POST",
                                data: data
                            }).then(function (data, status, headers, config) {
//                                    alert(JSON.stringify(data));
//                                      alert(JSON.stringify(data.data.maps.status).slice(1, -1));
//                                      $window.open("pdb_listing.action","_self"); //                alert(data.maps);
//            //                        Materialize.toast(data['maps']["status"], 4000);
                            });
                        }
                    });
        </script>
    </body>
</html>
