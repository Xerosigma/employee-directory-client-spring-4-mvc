<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Provider Client Spring4 MVC</title>
		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
		<meta name="viewport" content="width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes">
		
		<!-- Polyfill Web Components support for older browsers -->
		<script src="bower_components/platform/platform.js"></script>
		
		<!-- Import element -->
		<link rel="import" href="bower_components/google-map/google-map.html"/>
		<link rel="import" href="bower_components/google-map/google-map-directions.html"/>
		
		<link rel="import" href="bower_components/paper-elements/paper-elements.html">
		
		<link rel="import" href="../components/font-roboto/roboto.html">
		<link rel="import" href="bower_components/core-header-panel/core-header-panel.html"/>
		<link rel="import" href="bower_components/core-toolbar/core-toolbar.html"/>
		<link rel="import" href="bower_components/paper-tabs/paper-tabs.html"/>
    
	    <link rel="import" href="bower_components/core-icons/core-icons.html">
	    <link rel="import" href="bower_components/core-icons/maps-icons.html">
	    <link rel="import" href="bower_components/core-icons/social-icons.html">
	    <link rel="import" href="bower_components/core-selector/core-selector.html">
	    <link rel="import" href="bower_components/paper-icon-button/paper-icon-button.html">
    
		<link rel="import" href="elements/item-card.html"/>
	</head>
	<body unresolved>
	
		<core-header-panel>
			
			<core-toolbar layout vertical center>
				<paper-tabs id="tabs" selected="all" self-end>
					<paper-tab name="all">All</paper-tab>
					<paper-tab name="favorites">Favorites</paper-tab>
				</paper-tabs>
			</core-toolbar>

			<div class="container" layout vertical center>
			
				<c:forEach var="employee" items="${employees}">
					<!-- <a th:href="${employee.id.href}"></a> -->
					<item-card>
						<div>
							<img width="70" height="70" src="images/avatar-07.svg"/>
							<h2>${employee.name}</h2>
						</div>
						<div>
							<google-map latitude="${employee.location[0]}" longitude="${employee.location[1]}" disableDefaultUI fit>
								<google-map-marker latitude="${employee.location[0]}" longitude="${employee.location[1]}" draggable="false">
								</google-map-marker>
							</google-map>
						</div>
					</item-card>
				</c:forEach>
				<paper-fab icon="create" class="blue" role="button" tabindex="0" aria-label="create"></paper-fab>
			</div>
			<!-- main page content will go here --> 
		</core-header-panel>
		
		<style>
			html,body {
			  height: 100%;
			  margin: 0;
			  background-color: #E5E5E5;
			  font-family: 'RobotoDraft', sans-serif;
			}
			core-header-panel {
			  height: 100%;
			  overflow: auto;
			  -webkit-overflow-scrolling: touch; 
			}
			core-toolbar {
			  background: #03a9f4;
			  color: white;
			}
			#tabs {
			  width: 100%;
			  margin: 0;
			  -webkit-user-select: none;
			  -moz-user-select: none;
			  -ms-user-select: none;
			  user-select: none;
			  text-transform: uppercase;
			}
			.container {
			  width: 80%;
			  margin: 50px auto;
			}
			@media (min-width: 481px) {
			  #tabs {
			    width: 200px;
			  }
			  .container {
			    width: 400px;
			  }
			}
			#map { border-radius: 50%;}
		</style>
		
		<script>
		  var gmap = document.querySelector('google-map');
		  gmap.addEventListener('api-load', function(e) {
		    document.querySelector('google-map-directions').map = this.map;
		  });
		  
		  var tabs = document.querySelector('paper-tabs');
		  tabs.addEventListener('core-select', function() {
		    console.log("Selected: " + tabs.selected);
		  });
		</script>
	</body>
</html>