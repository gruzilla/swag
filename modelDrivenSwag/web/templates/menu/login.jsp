<div id="menu-content-login">
	<form>
		<input type="hidden" name="hashed" />
		<dl>
			<dt>
				<span class="reqd help" onclick="$(this).next('span').dialog($.extend($.swag.Main.DIALOG_DEFAULT, {title:'Required'}))">*</span>
				<span style="display:none">Username is required for login</span>
				<label for="username">Username</label>
			</dt>
			<dd>
				<input type="text" name="username" maxlength="50" />
				<div class="formhelp">Your username normally is your e-mail-address.</div>
			</dd>
			<dt>
				<span class="reqd help" onclick="$(this).next('span').dialog($.extend($.swag.Main.DIALOG_DEFAULT, {title:'Required'}))">*</span>
				<span style="display:none">You should have received your password by e-mail.</span>
				<label for="password">Password</label>
			</dt>
			<dd>
				<input type="password" name="password" maxlength="64" />
				<div class="formhelp">No password? <a href="user/register">Go here, register!</a><br />
				Forgot password? <a href="user/forgot">Go here, request it!</a></div>
			</dd>
			<dt></dt>
			<dd class="submit">
				<button class="submit" onclick="return false;">Log in</button>
			</dd>
		</dl>
	</form>
</div>