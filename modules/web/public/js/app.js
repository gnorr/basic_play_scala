var todoModule = angular.module('Todo', ['ngResource']);

todoModule.controller('TodoController', function($scope, TodoService) {
    $scope.items = TodoService.query();

    $scope.item = 'New Todo Item';

    $scope.createItem = function() {
        TodoService.save({}, {name:$scope.item}, function(item) {
            $scope.items.push(item)
        });
    };
});

todoModule.factory('TodoService', ['$resource', function($resource) {
    return $resource('/api/item/:id',
        {id:'@id'},
        {}
    );
}]);

todoModule.factory('TodoService1', function() {
    var getItems = function() {
        var tempItems = [
            {'id':1, 'name':'todo item 1'},
            {'id':2, 'name':'todo item 2'}
        ];
        return tempItems;
    };

    return {
        getItems: getItems
    };
});
