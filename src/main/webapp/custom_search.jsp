<!DOCTYPE html>
<html ng-app="plunker">

  <head>
    <meta charset="utf-8" />
    <title>AngularJS Plunker</title>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/animate.min.css" />
    <link rel="stylesheet" href="css/angular.css" />

  </head>

  <body ng-controller="MainCtrl">
      <div ng-tabs class="tabbable">
            <ul class="nav nav-tabs">
                <li ng-tab-head ="active">
                    <a href="#" ng-click="$event.preventDefault()">Vehicle</a>
                </li>
                <li ng-tab-head>
                    <a href="#" ng-click="$event.preventDefault()">PDB</a>
                </li>
            </ul>
            <div class="tab-content" style="overflow: hidden">
                <div ng-tab-body="animated " class="tab-pane">
                    <div class="col-md-6 float-left">
                            <div class="card">
                               <div class="card-block marketing-card p-t-20">
                                    <form class=""  name="myForm">
                                            <div class="form-group text-right">

                                            </div>                
                                            <div>
                                                <div ng-if="Demo.data">
                                                    <div class="form-group">
                                                        <label for="vehicle">Select vehicle:</label>
                                                        <input type="radio" ng-model="new_vehicle" value="select_vehicle"/>
                                                    </div>
                                                    <div class="form-group"  ng-if="new_vehicle=='select_vehicle'">
                                                        <label for="vehicle">vehicle:</label>
                                                        <select ng-model="data.vehicleversion" ng-change="LoadPreviousVersion()">
                                                            <s:iterator value="vehicleversion_result" >
                                                                <option value="<s:property value="id"/>"><s:property value="versionname"/></option>
                                                            </s:iterator>
                                                        </select>
                                                        <label for="vehicle">vehicle:</label>
                                                        <select ng-model="data.vehicleversion" ng-change="LoadPreviousVersion()">
                                                            <s:iterator value="vehicleversion_result" >
                                                                <option value="<s:property value="id"/>"><s:property value="versionname"/></option>
                                                            </s:iterator>
                                                        </select>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="vehicle">New vehicle:</label>
                                                        <input type="radio" ng-model="new_vehicle" value="new_vehicle"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="vehicle">Vehicle:</label>
                                                        <input type="text" class="form-control" placeholder="Enter vehicle" name="vehicle"  ng-model="data.vehiclename" required>
                                                        <span ng-show="myForm.vehicle.$touched && myForm.vehicle.$invalid">The name is required.</span>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="model">Model:</label>
                                                        <tags-input ng-model="data.modelname"  use-strings="true"></tags-input>
                                                    </div>                                                    
                                                </div>                                                    
                                            </div>
                <!--                                            <div class="form-group">
                                                <label for="status">Status:</label>
                                                <label class="switch float-right">
                                                  <input type="checkbox" ng-model="data.status">
                                                  <span class="slider round"></span>
                                                </label>
                                            </div>-->
                                            <div class="text-center">
                                                <button ng-show="showSave == true" type="submit" class="btn btn-default" ng-mousedown='doSubmit=true' ng-click="submit_vehicleversion('save')" name="save">Save</button>
                                                <button ng-show="showSubmit == true" type="submit" class="btn btn-default" ng-mousedown='doSubmit=true' ng-click="submit_vehicleversion('submit')" name="submit">Submit</button>
                                            </div>
                                     </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 float-left">
                            <div class="card">
                                <div class="row card-block marketing-card">
                                    <div class="col-md-12 mod_vec_animate" ng-if="Demo.data">
                                        <h5 class="m-t-20"><i class="icofont icofont-steering"></i> {{data.vehiclename}}</h5>
                                        <ul>
                                            <li ng-repeat="i in data.modelname">

                                                <i class="icofont icofont-whisle text-c-red"></i> {{i}} 
                                            </li>
                                        </ul>
                                    </div> 
                                </div>
                            </div>
                        </div>
                </div><!--tab-body-->
                <div ng-tab-body="animated " class="tab-pane">
                    
                </div><!--tab-body-->
            </div>
        </div>
  </body>
  
<script src="js/lib/angular.min.js"></script>  
<script src="js/tabs.js"></script>
  <script>
    var app = angular.module('plunker', ['tabs']);
    app.controller('MainCtrl', function() {
      
    });
  </script>
</html>