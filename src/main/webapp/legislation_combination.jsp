<%@include file="header.jsp" %>
<%@include file="sidebar.jsp" %>
<!--<link rel="stylesheet" type="text/css" href="css/query-builder.default.min.css">
<script type="text/javascript" src="js/query-builder.min.js"></script> 

<script type="text/javascript" src="js/select2.js"></script>
<link rel="stylesheet" type="text/css" href="css/select2.css">

<link rel="stylesheet" type="text/css" href="css/combineBuilder.css">
<script type="text/javascript" src="js/combineBuilder.js"></script>-->
<style>
    .group{
    background-color: #fff;
    padding: 15px;
    border-radius: 5px;
    border: solid 1px #666;
}

.group-conditions{
    margin-left: 20px;
}

.alert-group{
    margin-top: 10px;
    margin-bottom: 10px;
    border-color: rgb(192, 152, 83);
    border-color: rgb(251, 238, 213);
    border-color: rgb(220, 200, 150);
}

.condition, .group{
    margin-top: 15px;
    margin-bottom: 15px;
}
</style>
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
                                    <h4>Combinations</h4>
                                    <span>Features Listing</span>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="page-header-breadcrumb">
                                <ul class="breadcrumb-title">
                                    <li class="breadcrumb-item">
                                        <a href="index.html"> <i class="icofont icofont-home"></i> </a>
                                    </li>
                                    <li class="breadcrumb-item">
                                        <s:url action="legislation.action" var="aURL" />
                                        <s:a href="%{aURL}">
                                            Back
                                        </s:a>
                                    </li>
                                    <li class="breadcrumb-item">
                                        <a class="waves-effect waves-light modal-trigger" href="#modal-product-form" ng-click="resets();showCreateForm()">Add Combination</a> 
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
                                    <form class="form-inline mt-3">
                                        <div class="form-group">
                                            <input type="text" ng-model="search" class="form-control" placeholder="Search">
                                        </div>
                                    </form>
                                    <table st-table="rowCollection" class="table table-striped">
                                        <thead>
                                            <tr>

                                                <th ng-click="sort('version')" class="text-center">No</th>
                                                <th ng-click="sort('version')" class="text-center">Legislation</th>
                                                <th ng-click="sort('version')" class="text-center">Status</th>
                                                <th ng-click="sort('created_date')" class="text-center">Created Date</th>
                                                <th ng-click="sort('modified_date')" class="text-center">Modified Date</th>
                                                <th ng-click="sort('action')" class="text-center">Action</th>

                                            </tr>
                                        </thead>
                                        <tbody ng-init="getAllLegislation()">

                                            <tr dir-paginate="record in legislation|orderBy:sortKey:reverse|filter:search|itemsPerPage:5">
                                                <td style="display:none;" class="combination">{{record.combination}}</td>
                                                <td style="display:none;" class="combination_id">{{record.leg_id}}</td>
                                                <td class="text-center">

                                                    {{$index + 1}}

                                                </td>
                                                <td class="text-center">
                                                    <a id="leg_name" class="mytooltip p-l-10 p-r-10 blink" href="javascript:void(0)"> 
                                                        {{record.leg}}
                                                        <span class="tooltip-content5">
                                                            <span class="tooltip-text3">
                                                                <span class="tooltip-inner2">
                                                                    <span>{{record.gp}}</span>
                                                                </span>
                                                            </span>
                                                        </span>
                                                    </a>    
                                                </td>
                                                <td class="text-center">                           
                                                    <button class="btn btn-default btn-bg-c-blue btn-outline-default btn-round btn-action" ng-if="record.status == true">Active
                                                    </button>

                                                    <button class="btn btn-default btn-bg-c-yellow btn-outline-default btn-round btn-action" ng-if="record.status == false">Inactive
                                                    </button>
                                                </td>
<!--                                                <td class="text-center">

                                                    <a href="#" ng-click="removeRow(record.fid)"><i class="icofont icofont-ui-close text-c-red"></i></a>

                                                </td>-->
                                                <td class="text-center">{{record.created_date}}</td>
                                                <td class="text-center">{{record.modified_date}}</td>
                                                <td class="text-center"> 
                                                    <button class="btn btn-default btn-bg-c-blue btn-outline-primary btn-round modal-trigger" data-ng-click="view_and_edit('edit', record.combination, record.leg)" id="edit_or_view" name="edit" ng-if="record.status == false" data-target="modal-product-form">Edit</button>
                                                    <button class="btn btn-default btn-bg-c-blue btn-outline-danger btn-round modal-trigger" data-ng-click="view_and_edit('view', record.combination, record.leg)" id="edit_or_view" name="view" ng-if="record.status == true" data-target="modal-product-form">view</button>
<!--                                                    <button class="btn btn-default btn-bg-c-blue btn-outline-primary btn-round modal-trigger" id="edit_or_view" name="edit" ng-click="view_and_edit()" ng-if="record.status == false" data-target="modal-product-form">Edit</button>
                                                    <button class="btn btn-default btn-bg-c-blue btn-outline-danger btn-round modal-trigger" id="edit_or_view" name="view" ng-click="view_and_edit()" ng-if="record.status == true" data-target="modal-product-form">view</button>
                                                    <button class="btn btn-success set-sql" data-target="import_export" id="btn-set1">Set rules from SQL</button>
                                                    <button class="btn btn-default btn-bg-c-blue btn-outline-danger btn-round" data-target="import_export" id="btn-set1">Set rules from SQL</button>-->
                                                </td>

                                            </tr>

                                        </tbody>
                                    </table>
                                    <dir-pagination-controls
                                        max-size="5"
                                        direction-links="true"
                                        boundary-links="true" >
                                    </dir-pagination-controls>

                                </div>
                            </div>
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
                                     <input type="text" id="combname" ng-model="combname" name="combname" placeholder="Name" class="col-md-12"/>
                                     <input type="hidden" id="combid" ng-model="combid" />
                                     <input type="hidden" id="button_status" ng-model="button_status" />
                                 </div>
                                 <query-builder group="filter.group" ></query-builder>
                                 <div class="col-md-12 col-lg-offset-1">
                                     <div id="builder-basic" style="display: block;"></div>
                                     <div class="btn-group float-right">                                                    
                                         <button class="btn btn-primary parse-sql  float-right" data-target="import_export" data-stmt="false" id="btn-get" data-ctype="legislation" ng-click="addCombination($element.target)" >Submit</button>                                                        
                                     </div>
                                 </div>
                            </div>
                        </div>
             
                        <%@include file="footer.jsp" %>
                        <!--<script src="js/lib/materialize.min.js"></script>-->
                        <!--<script src="js/dirPagination.js"></script>-->
                        <script type="text/ng-template" id="/queryBuilderDirective.html" >
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
                                                <select ng-options="t as t.name for t in fields track by t.name" ng-model="rule.field" myval="{{rule.field}}" class="form-control input-sm"></select>
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
                        <script>
                            //        var app = angular.module('angularTable', ['angularUtils.directives.dirPagination']);

                            app.controller('RecordCtrl1', function($scope, $http, $window)
                            {
                                $http.get("fetch_legislation_combination.action").then(function (data, status, headers, config)
                                {
                                    for (var item in data.data.result_data) {
//                                    alert(JSON.stringify(JSON.parse(data.data.result_data[item]['combination'].replace(/&quot;\&quot;/g,'"'))));
                                        data.data.result_data[item]['combination'] = JSON.parse(data.data.result_data[item]['combination'].replace(/&quot;\&quot;/g,'"'));
                                        data.data.result_data[item]['gp'] = computed(data.data.result_data[item]['combination'].group, "listing");
                                    }
                                    $scope.legislation = data.data.result_data;
//                                    alert(JSON.stringify(data.data.result_data));
                                });
                                this.data = [];
//                                $scope.legislation = JSON.parse("<s:property value="result_data_obj"/>".replace(/&quot;/g,'"'));
//                                $window.alert(JSON.stringify($scope.legislation));
                                
                                 var data = '{"group": {"operator": "AND","rules": []}}';
                                 
                                $scope.resets = function() {
//                                    alert('Hai');
                                    $scope.filter = JSON.parse(data);
                                    $scope.combname = "";
                                    $scope.combid = "";
                                    $scope.button_status = "";
                                }
                                function htmlEntities(str) {
                                    return String(str).replace(/</g, '&lt;').replace(/>/g, '&gt;');
                                }

                                function computed(group, forWhat) {
                                    if (!group) return "";
                                    if (forWhat == "output") {
                                        for (var str = "(", i = 0; i < group.rules.length; i++) {
                                            i > 0 && (str += " <strong>" + group.operator + "</strong> ");
                                            str += group.rules[i].group ?
                                                computed(group.rules[i].group, "output") :
                                                group.rules[i].field.name+"="+group.rules[i].field.id + " " + htmlEntities(group.rules[i].condition) + " " + group.rules[i].data;
                                        }
                                        return str + ")";
                                    } else if (forWhat == "listing") {
                                        for (var str = "(", i = 0; i < group.rules.length; i++) {
                                            i > 0 && (str += " " + group.operator + " ");
                                            str += group.rules[i].group ?
                                                computed(group.rules[i].group, "listing") :
                                                group.rules[i].field.name+"="+group.rules[i].field.id + " " + htmlEntities(group.rules[i].condition) + " " + group.rules[i].data;
                                        }
                                        return str + ")";
                                    }
                                }

                                $scope.json = null;

                                $scope.filter = JSON.parse(data);
//                                alert(JSON.stringify($scope.filter));
                                $scope.$watch('filter', function (newValue) {
                                    $scope.json = JSON.stringify(newValue, null, 2);
                                    $scope.output = computed(newValue.group, "output");
//                                    alert("2121212121 "+computed(newValue.group));
                                }, true);                            
//                                $scope.getAllLegislation = function(){
//                                    //                alert("getall");
//                                    $http.get("features_list.action")
//                                        .then(function(response, data, status, headers, config){
//                                            $window.alert(JSON.stringify(response.data.maps_object));
//                                    });
//                                }
                            $scope.addCombination = function(btn){
//                                    $scope.filter = {};
                                if ($scope.combname) {
                                    var result = {};
//                                    var result = $('#builder-basic').queryBuilder('getSQL', false);
                                    result['qb_name'] = $scope.combname;
//                                    var ctype = btn.getAttribute('data-ctype').value;
//                                        var myButton = angular.element(document.querySelector('.btn-primary'));
                                    result['ctype'] = angular.element(document.querySelector('.btn-primary')).data('ctype');
//                                        $window.alert(ctype);
                                    var url_link = "";
                                    result['sql'] = $scope.filter;
//                                    result['sql'] = $scope.output;

                                    if(result['ctype'] == "safety")
                                        url_link = "createsafety_comb";
                                    else
                                        url_link = "createlegislation_comb";

                                    if($scope.button_status == "edit")
                                        result['cid'] = $scope.combid;

                                    result['qb_status'] = true;

                                    alert(JSON.stringify(result) +" "+url_link);

                                    if (result && url_link !== "") {
                                        $http({
                                            url: url_link,
                                            method: "POST",
                                            data: result,
                                        }).then(function (response, status, headers, config){
                                            alert(JSON.stringify(response.data.maps.status));
                                            location.reload();
                                        });
                                    } else {
                                        alert("Missing Some values");
                                    }
                                } else {
                                    alert("Fill the Data");
                                }
                            }
                            $scope.view_and_edit = function(element, combination, leg) {
//                                var btnName = element.name;
                                if (element == "view") {
                                    $scope.combname = leg;
//                                    for (var i = 0, max = combination.group.rules.length; i < max; i++) {
//                                        if (combination.group.rules[i].group) {
//                                            var name = combination.group.rules[i].group.rules[i].field.name;
//                                            combination.group.rules[i].group.rules[i].field = name;
//                                            alert("DSADASD"+combination.group.rules[i].field);
//                                        } else {
//                                            var name = combination.group.rules[i].field.name;
//                                            combination.group.rules[i].field = name;
//                                            alert("DSADdfdsfdsASD"+combination.group.rules[i].field);
//                                        }
//                                    }
                                    $scope.filter = combination;
//                                    alert(JSON.stringify($scope.filter));
                                } else {
                                    $scope.filter = combination;
//                                    alert(JSON.stringify($scope.filter));
                                }
                                // initialize modal
                                $('.modal-trigger').leanModal();
                            }
                            
                            });
                            var queryBuilder = angular.module('queryBuilder', []);
                            queryBuilder.directive('queryBuilder', ['$compile','$http', function ($compile, $http) {
                                    
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
                                                scope.fields = [];
                                                $http.get("features_list.action")
                                                    .then(function(response, data, status, headers, config){
                                                        if (response.data.maps_object.result_data_obj) {
                                                            var features = response.data.maps_object.result_data_obj;
    //                                                        alert(JSON.stringify(features));
                                                            for (var item in features) {
                                                                scope.fields.push({ id: features[item].fid,
                                                                    name: features[item].feature_name });
                                                            }
//                                                            alert(JSON.stringify(scope.fields));
                                                        } else {
                                                            alert("Features not Found");
                                                        }
                                                });
                                                
//                                                    scope.fields = [
//                                                        { name: 'f1' },
//                                                        { name: 'f2' },
//                                                        { name: 'f3' },
//                                                        { name: 'f4' },
//                                                        { name: 'f5' }
//                                                    ];

                                                scope.conditions = [

                                                    { name: true },
                                                    { name: false }
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
                                                        field_id: '',
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
                            app.filter('customSplitString', function()
                            {
                                return function(input)
                                {
                                    if (input != undefined){
                                    var arr = input.split(',');
                                    return arr;
                                }
                                };
                            });
                        </script>   
                        </body>

                        </html>                                            