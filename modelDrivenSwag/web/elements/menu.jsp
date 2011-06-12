<ul id="menu">
	<li data-js-controller="swag.menu.Login" data-show="not-authenticated">Login</li>
	<li data-js-controller="swag.menu.Logout" data-show="authenticated">Logout</li>
	<li data-js-controller="swag.menu.Account" data-show="authenticated">Account</li>
	<li data-js-controller="swag.menu.Chat" data-show="authenticated">Chat</li>
	<li data-js-controller="swag.menu.Map" data-show="authenticated">Map</li>
</ul>
<div id="menu-templates" style="display:none">
	<%@include file="../templates/menu/login.jsp" %>
	<%@include file="../templates/menu/account.jsp" %>
	<%@include file="../templates/menu/chat.jsp" %>
	<%@include file="../templates/menu/map.jsp" %>
</div>