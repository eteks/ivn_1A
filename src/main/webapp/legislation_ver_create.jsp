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
                                                        <h4>Legislation</h4>
                                                        <span>Legislation Listing</span>
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
                                                            <s:url action="legislation.action" var="aURL" />
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
                                                    <div class="card-block marketing-card p-t-0">
                                                         <div class="row p-t-30">
                                                            <div class="form-group col-md-3">
                                                                <label for="vehicle">Vehicle version :</label>
                                                                <select ng-model="data.vehicleversion" ng-change="LoadSelectedVehicleVersionData()">
                                                                    <s:iterator value="vehicleversion_result" >
                                                                        <option value="<s:property value="id"/>">
                                                                            <s:property value="versionname"/>
                                                                        </option>
                                                                    </s:iterator>
                                                                </select>
                                                            </div>
                                                            <div class="form-group col-md-3">
                                                                <label for="vehicle">Vehicle:</label>
                                                                <select ng-hide="data.vehicleversion"></select>
                                                                <select ng-change="LoadVehicleModels(data.vehiclename)" ng-if="vehicle_list.length > 0" ng-model="data.vehiclename">
                                                                        <option value="{{veh.vehicle_id}}" ng-repeat="veh in vehicle_list">{{veh.vehiclename}}</option>                                                                    
                                                                </select>
                                                            </div>
                                                            <div class="form-group col-md-3">
                                                                <label for="vehicle">Legislation version :</label>
                                                                <select ng-model="data.pdbversion" ng-change="LoadPDBPreviousVersion()">
                                                                    <s:iterator value="pdbversion_result" >
                                                                        <option value="<s:property value="id"/>">
                                                                            <s:property value="pdb_versionname"/>
                                                                        </option>
                                                                    </s:iterator>
                                                                </select>
                                                            </div>
                                                            <a class="waves-effect waves-light modal-trigger" href="#modal-product-form" ng-click="showCreateForm()">Add Combination</a>
                                                        </div>   
                                                            
                                                        <table st-table="rowCollection" class="table table-striped">
                                                                <thead>
                                                                <tr>
                                                                    
                                                                    <th class="">Legislation</th>
                                                                    <th class="text-center" ng-repeat="i in records">
                                                                        {{i.modelname}}
                                                                    </th>

                                                                </tr>
                                                                </thead>
                                                                
                                                                <tbody>
                                                                <form ng-model="myform">    
                                                                    <tr dir-paginate="record in legislation|orderBy:sortKey:reverse|filter:search|itemsPerPage:20">
                                                                        
                                                                       
                                                                        <td class="">
                                                                            <a href="#" ng-click="removeRow(record.fid)"><i class="icofont icofont-ui-close text-c-red"></i></a> {{record.leg}}
                                                                        </td>
                                                                        <td class="text-center" ng-repeat="i in records">                                                                             
                                                                              <label class="custom_radio mytooltip tooltip-effect-8">                                                                                
                                                                                <input type="radio" ng-click="radiovalue(record.fid,i.vehicle_model_mapping_id,'y')" name="f{{record.fid}}_{{i.vehicle_model_mapping_id}}" value="y" class="radio_button">
                                                                                <span class="checkmark c_b_g">                                                                                    
                                                                                </span>
                                                                                <span class="tooltip-content2">yes</span>
                                                                              </label>
                                                                              <label class="custom_radio mytooltip tooltip-effect-8">
                                                                                <input type="radio" ng-click="radiovalue(record.fid,i.vehicle_model_mapping_id,'n')" name="f{{record.fid}}_{{i.vehicle_model_mapping_id}}" value="n" class="radio_button">
                                                                                <span class="checkmark c_b_r"></span>
                                                                                <span class="tooltip-content2">no</span>
                                                                              </label>
                                                                              <label class="custom_radio mytooltip tooltip-effect-8">
                                                                                <input type="radio" ng-click="radiovalue(record.fid,i.vehicle_model_mapping_id,'o')" name="f{{record.fid}}_{{i.vehicle_model_mapping_id}}" value="o" class="radio_button">    
                                                                                <span class="checkmark c_b_b"></span>
                                                                                <span class="tooltip-content2">optional</span>
                                                                              </label>
                                                                        </td>
                                                                    </tr>
                                                                </form>
                                                                </tbody>
                                                            </table>
                                                        <dir-pagination-controls
                                                                max-size="20"
                                                                direction-links="true"
                                                                boundary-links="true">
                                                        </dir-pagination-controls>                                                        
                                                    </div>
                                                </div>
                                            </div>
                <div class="col-lg-12 text-right">
                 
                    <div id="modal-upload" class="modal">
                        <div class="modal-content">
                            <h5 class="text-c-red m-b-10"><a class="modal-action modal-close waves-effect waves-light float-right m-t-5" ><i class="icofont icofont-ui-close"></i></a></h5>
                            <form class="float-left">
                                <input type="file" ng-model="" name=""/>
                                 <button ng-show="showSubmit == true" type="submit" class="btn btn-primary">Upload</button>
                            </form>

                        </div>
                    </div>
                    <label for="status" style="vertical-align:middle">Status:</label>
                    <label class="switch m-r-50"  style="vertical-align:middle">
                        <input type="checkbox" ng-model="data.status">
                        <span class="slider round"></span>
                     </label>

                    <button ng-show="showSave == true" type="submit" class="btn btn-primary" ng-mousedown='doSubmit=true' ng-click="checkNotify('save')" name="save">Save</button>
                    <button ng-show="showSubmit == true" type="submit" class="btn btn-primary" ng-mousedown='doSubmit=true' ng-click="checkNotify('submit')" name="submit">Submit</button>

                </div>
                <!-- Marketing End -->
                        <div id="modal-product-form" class="modal">
                            <div class="modal-content">
                                <h5 class="text-c-red m-b-25">Feature Combination <a class="modal-action modal-close waves-effect waves-light float-right m-t-5" ><i class="icofont icofont-ui-close"></i></a></h5>                                
                                
                                           <div class="alert alert-info">
                                                <strong>Output</strong><br>
                                                <span ng-bind-html="output"></span>
                                            </div>
                                            <div class="col-md-12 col-lg-offset-1">
                                                <input type="text" id="combname" name="combname" placeholder="Name" class="col-md-12"/>
                                                <input type="hidden" id="combid"/>
                                                <input type="hidden" id="button_status"/>
                                            </div></br>
                                            <query-builder group="filter.group"></query-builder>
                                            <div class="col-md-12 col-lg-offset-1">
                                                <div id="builder-basic" style="display: block;"></div>
                                                <div class="btn-group float-right">                                                    
                                                        <button class="btn btn-primary parse-sql  float-right" data-target="import_export" data-stmt="false" id="btn-get" data-ctype="safety">Submit</button>                                                        
                                                </div>
                                            </div>
                                
                            </div>
                        </div>
             
                        <%@include file="footer.jsp" %>
                        <!--<script src="js/lib/materialize.min.js"></script>-->
                        <!--<script src="js/dirPagination.js"></script>-->
                        <script type="text/ng-template" id="/queryBuilderDirective.html">
                        <div class="alert alert-warning alert-group">
                            <div class="form-inline">
                                <select ng-options="o.name as o.name for o in operators" ng-model="group.operator" class="form-control input-sm"></select>
                                <button style="margin-left: 5px" ng-click="addCondition()" class="btn btn-sm btn-success"><span class="glyphicon glyphicon-plus-sign"></span> Add Condition</button>
                                <button style="margin-left: 5px" ng-click="addGroup()" class="btn btn-sm btn-success"><span class="glyphicon glyphicon-plus-sign"></span> Add Group</button>
                                <button style="margin-left: 5px" ng-click="removeGroup()" class="btn btn-sm btn-danger"><span class="glyphicon glyphicon-minus-sign"></span> Remove Group</button>
                            </div>
                            <div class="group-conditions">
                                <div ng-repeat="rule in group.rules | orderBy:'index'" class="condition">
                                    <div ng-switch="rule.hasOwnProperty('group')">
                                        <div ng-switch-when="true">
                                            <query-builder group="rule.group"></query-builder>
                                        </div>
                                        <div ng-switch-default="ng-switch-default">
                                            <div class="form-inline">
                                                <select ng-options="t.name as t.name for t in fields" ng-model="rule.field" class="form-control input-sm"></select>
                                                <select style="margin-left: 5px" ng-options="c.name as c.name for c in conditions" ng-model="rule.condition" class="form-control input-sm"></select>
                                                <!-- <input style="margin-left: 5px" type="text" ng-model="rule.data" class="form-control input-sm"/> -->
                                                <button style="margin-left: 5px" ng-click="removeCondition($index)" class="btn btn-sm btn-danger">X</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </script>
<!--<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>-->
  <!--<script src="js/dirPagination.js"></script>-->
    <script>
        app.controller('RecordCtrl1', function($scope, $http)
                            {
                                 var data = '{"group": {"operator": "AND","rules": []}}';

                                function htmlEntities(str) {
                                    return String(str).replace(/</g, '&lt;').replace(/>/g, '&gt;');
                                }

                                function computed(group) {
                                    if (!group) return "";
                                    for (var str = "(", i = 0; i < group.rules.length; i++) {
                                        i > 0 && (str += " <strong>" + group.operator + "</strong> ");
                                        str += group.rules[i].group ?
                                            computed(group.rules[i].group) :
                                            group.rules[i].field + " " + htmlEntities(group.rules[i].condition) + " " + group.rules[i].data;
                                    }

                                    return str + ")";
                                }

                                $scope.json = null;

                                $scope.filter = JSON.parse(data);

                                $scope.$watch('filter', function (newValue) {
                                    $scope.json = JSON.stringify(newValue, null, 2);
                                    $scope.output = computed(newValue.group);
                                }, true);                            

                            });
                            var queryBuilder = angular.module('queryBuilder', []);
                            queryBuilder.directive('queryBuilder', ['$compile', function ($compile) {
                                return {
                                    restrict: 'E',
                                    scope: {
                                        group: '='
                                    },
                                    templateUrl: '/queryBuilderDirective.html',
                                    compile: function (element, attrs) {
                                        var content, directive;
                                        content = element.contents().remove();
                                        return function (scope, element, attrs) {
                                            scope.operators = [
                                                { name: 'AND' },
                                                { name: 'OR' }
                                            ];

                                            scope.fields = [
                                                { name: 'f1' },
                                                { name: 'f2' },
                                                { name: 'f3' },
                                                { name: 'f4' },
                                                { name: 'f5' }
                                            ];

                                            scope.conditions = [

                                                { name: '1' },
                                                { name: '0' }
                                                // { name: '=' },
                                                // { name: '<>' },
                                                // { name: '<' },
                                                // { name: '<=' },
                                                // { name: '>' },
                                                // { name: '>=' }
                                            ];

                                            scope.addCondition = function () {
                                                scope.group.rules.push({
                                                    condition: '=',
                                                    field: '',
                                                    data: ''
                                                });
                                            };

                                            scope.removeCondition = function (index) {
                                                scope.group.rules.splice(index, 1);
                                            };

                                            scope.addGroup = function () {
                                                scope.group.rules.push({
                                                    group: {
                                                        operator: 'AND',
                                                        rules: []
                                                    }
                                                });
                                            };

                                            scope.removeGroup = function () {
                                                "group" in scope.$parent && scope.$parent.group.rules.splice(scope.$parent.$index, 1);
                                            };

                                            directive || (directive = $compile(content));

                                            element.append(directive(scope, function ($compile) {
                                                return $compile;
                                            }));
                                        } 
                                    }
                                }
                            }]);

    $(document).ready(function(){
        // initialize modal
        $('.modal-trigger').leanModal();
    });
    </script>   
</body>

</html>          
