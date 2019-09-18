<%@include file="header.jsp" %>
<%@include file="sidebar.jsp" %>

                    <div class="pcoded-content" ng-controller="RecordCtrl1 as Demo">
                        <div class="pcoded-inner-content">
                            <div class="main-body">

                                <div class="page-wrapper">

                                    <div class="page-header card">
                                        <div class="row align-items-end">
                                            <div class="col-lg-8">
                                                <div class="page-header-title">
                                                    <i class="icofont  icofont-mining bg-c-red"></i>
                                                    <div class="d-inline">
                                                        <h4>Feature Version</h4>
                                                        <span>Create</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-4">
                                                <div class="page-header-breadcrumb">
                                                    <ul class="breadcrumb-title">
                                                        <li class="breadcrumb-item">
                                                            <a href="index.html"> <i class="icofont icofont-home"></i></a>
                                                        </li>
                                                        <li class="breadcrumb-item">
                                                            <s:url action="feature.action" var="aURL" />
                                                            <s:a href="%{aURL}">
                                                                Back
                                                            </s:a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="page-body">
                                        <div class="row">       
                                            <!-- Marketing Start -->
                                            <div class="col-md-12">
                                                <div class="card">
                                                    <div class="card-block marketing-card p-t-0 text-center">
                                                         <div class="row p-t-30">
                                                            
                                                            <div class="form-group col-md-3">
                                                                <label for="vehicle"> Select Vehicle:</label>
                                                                <select ng-hide="data.vehicleversion"></select>
                                                                <select ng-change="LoadVehicleModels(data.vehiclename)" ng-if="vehicle_list.length > 0" ng-model="data.vehiclename">
                                                                        <option value="{{veh.vehicle_id}}" ng-repeat="veh in vehicle_list">{{veh.vehiclename}}</option>                                                                    
                                                                </select>
                                                            </div>
                                                            
                                                            
                                                        </div>   
                                                            
                                                        <!--drag and drop-->
                                                        <script type="text/ng-template" id="list.html">
                                                            <div ng-repeat="list in lists" class="col-md-4 drop_list_box">
                                                              <div class="panel panel-info">
                                                                <div class="panel-heading">
                                                                  <h5 class="panel-title">List of {{list.label}} </h5>
                                                                </div>
                                                                <ul dnd-list="list.people"
                                                                          dnd-allowed-types="list.allowedTypes"
                                                                          dnd-disable-if="list.people.length == list.max-1">

                                                                          <li ng-repeat="person in list.people"
                                                                              dnd-draggable="person"
                                                                              dnd-type="person.type"
                                                                              dnd-disable-if="person.type == 'unknown'"
                                                                              dnd-moved="list.people.splice($index, 1)"
                                                                              class="background-{{person.type}}"
                                                                              >
                                                                              {{person.name}}
                                                                          </li>

                                                                          <li class="dndPlaceholder">
                                                                              Drop any <strong>{{list.allowedTypes.join(' or ')}}</strong> here
                                                                          </li>

                                                                      </ul>
                                                              </div>
                                                            </div>
                                                           </script> 

                                                          <!-- This template is responsible for rendering a container element. It uses
                                                           the above list template to render each container column -->
                                                          <script type="text/ng-template" id="container.html">
                                                            <div class="container-element box box-blue">
                                                              <h3>Container {{item.id}}</h3>
                                                              <div style="padding:125px" class="column" ng-repeat="lists in item.columns" ng-include="'list.html'"></div>

                                                            </div>
                                                          </script>

                                                          <!-- Template for a normal list item -->
                                                          <script type="text/ng-template" id="item.html">
                                                            <div class="item">Item {{item.id}}</div>
                                                          </script>

                                                          <!-- Main area with dropzones and source code -->
                                                          <div class="nestedDemo">
                                                            <div class="col-sm-12">
                                                              <div class="row">
                                                                <div ng-repeat="(zone, lists) in models.dropzones" class="col-md-12">
                                                                  <div class="dropzone box box-yellow">
                                                                    <!-- The dropzone also uses the list template -->
                                                                    <h3>Dropzone</h3>
                                                                    <div ng-include="'list.html'"></div>
                                                                  </div>
                                                                </div>
                                                              </div>

                                                              <div view-source="nested" ></div>

                                                              <h2>Generated Model</h2>
                                                              <pre class="text-left">{{modelAsJson}}</pre>
                                                            </div>

                                                            <!-- Sidebar -->

                                                          </div>
                                                        <!--end drag and drop-->
                                                        
                                                    </div>
                                                </div>
                                            </div>
                <div class="text-right p-10">
                    <label for="status" style="vertical-align:middle">Status:</label>
                    <label class="switch m-r-50"  style="vertical-align:middle">
                        <input type="checkbox" ng-model="data.status">
                        <span class="slider round"></span>
                     </label>
                     

                    <a class="feature_add_tip modal-trigger btn-floating btn-primary" ng-show="showProceed == true" style="padding:10px" href="#modal-comment" >Proceed</a>
                    <div id="modal-comment" class="modal">
                         <div class="modal-content text-left">

                             <h5 class="text-c-red m-b-10">Comment <a class="modal-action modal-close waves-effect waves-light float-right m-t-5" ><i class="icofont icofont-ui-close"></i></a></h5>
                             <textarea class="col-md-12 m-b-10" ng-model="data.pdb_manual_comment"></textarea>
                             <div ng-if="create_type == true">
                                 <input type="radio" ng-click="" ng-model="data.version_change" value="major" class="radio_button">Major
                                 &nbsp;<input type="radio" ng-click="" ng-model="data.version_change" value="minor" class="radio_button">Minor
                             </div>
                             <div class="text-right">
                                 <button  type="submit" class="btn btn-primary"  name="save">Save</button>
                                 <button  type="submit" class="btn btn-primary"  name="submit">Submit</button>
                             </div>
                         </div>
                     </div>
                </div>
                <!-- Marketing End -->
                        
             
                <%@include file="footer.jsp" %>
                <!--<script src="js/lib/materialize.min.js"></script>-->
                <!--<script src="js/dirPagination.js"></script>-->
                        
<!--<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>-->
  <!--<script src="js/dirPagination.js"></script>-->
    <script>
//        var app = angular.module('angularTable', ['angularUtils.directives.dirPagination']);

        app.controller('RecordCtrl1', function($scope, $http)
        {
             $scope.dropCallback = function(index, item, external, type) {
                // Return false here to cancel drop. Return true if you insert the item yourself.
                // roll down and delete any empty columns//
                var model = $scope.models.dropzones;
                for (var y in model.B) {
                  for (var zz in model.B[y].columns) {
                    var myColumns = [];
                    var foundThem = false;
                    if (Array.isArray(model.B[y].columns[zz])) {
                      $scope.models.dropzones.B[y].columns.splice(zz, 1);
                    } 
                  }
                }

                return item;
              };

              $scope.models = {
                selected: null,
                templates: [{
                  type: "item",
                  id: 2
                }, {
                  type: "container",
                  id: 1,
                  columns: [
                    []
                  ]
                }],
                dropzones: {
                  "B": [
                        {
                          label: "PDB",
                          allowedTypes: ['man'],
                          max: 3,
                          people: [
                              {name: "PDB 1.0", type: "pdb"},
                              {name: "PDB 2.0", type: "pdb"},
                              {name: "PDB 3.0", type: "pdb"}
                          ]
                      },
                      {
                          label: "Safety",
                          allowedTypes: ['safety'],
                          max: 3,
                          people: [
                              {name: "Safety 1.0", type: "safety"},
                              {name: "Safety 2.0", type: "safety"},
                              {name: "Safety 3.0", type: "safety"}
                          ]
                      },
                      {
                          label: "Legislation",
                          allowedTypes: ['legislation'],
                          max: 3,
                          people: [
                              {name: "Legislation 1.0", type: "legislation"},
                              {name: "Legislation 2.0", type: "legislation"},
                              {name: "Legislation 3.0", type: "legislation"}
                          ]
                      },
                      {
                          label: "Feature version",
                          allowedTypes: ['pdb', 'safety','legislation'],
                          max: 4,
                          people: []
                      }
                  ]
                }
              };

              $scope.$watch('models.dropzones', function(model) {
                $scope.modelAsJson = angular.toJson(model, true);
              }, true);                            

        });
                           

    $(document).ready(function(){
        // initialize modal
        $('.modal-trigger').leanModal();
    });
    </script>   
</body>

</html>          
