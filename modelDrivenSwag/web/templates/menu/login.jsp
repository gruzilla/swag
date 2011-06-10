<div id="menu-content-login">
	<div class="renew-password-form">
		<form>
			<dl>
				<dt>
					<span class="reqd help" onclick="$(this).next('span').dialog($.extend($.swag.Main.DIALOG_DEFAULT, {title:'Required'}))">*</span>
					<span style="display:none">E-Mail is required for renewing your password</span>
					<label for="email">E-Mail</label>
				</dt>
				<dd>
					<input type="text" name="email" maxlength="50" />
					<div class="formhelp">You'll receive an e-mail with a new password.</div>
				</dd>
				<dt></dt>
				<dd class="submit">
					<button class="submit" onclick="return false;">Request</button>
				</dd>
			</dl>
		</form>
		<a href="#login">abort</a>
	</div>
	<div class="register-form">
		<form>
			<dl>
				<dt>
					<span class="reqd help" onclick="$(this).next('span').dialog($.extend($.swag.Main.DIALOG_DEFAULT, {title:'Required'}))">*</span>
					<span style="display:none">A unique username is required for registering</span>
					<label for="username">Username</label>
				</dt>
				<dd>
					<input type="text" name="username" maxlength="50" /><span id="unique-username"></span>
					<div class="formhelp">You need an unique username.</div>
				</dd>
				<dt>
					<span class="reqd help" onclick="$(this).next('span').dialog($.extend($.swag.Main.DIALOG_DEFAULT, {title:'Required'}))">*</span>
					<span style="display:none">E-Mail is required for notifications and password reset</span>
					<label for="email">E-Mail</label>
				</dt>
				<dd>
					<input type="text" name="email" maxlength="50" />
				</dd>
				<dt>
					<span class="reqd help" onclick="$(this).next('span').dialog($.extend($.swag.Main.DIALOG_DEFAULT, {title:'Required'}))">*</span>
					<span style="display:none">Your real name is needed for billing</span>
					<label for="name">Real name</label>
				</dt>
				<dd>
					<input type="text" name="name" maxlength="50" />
				</dd>
				<dt>
					<span class="reqd help" onclick="$(this).next('span').dialog($.extend($.swag.Main.DIALOG_DEFAULT, {title:'Required'}))">*</span>
					<span style="display:none">Your full address is needed for billing.</span>
					<label for="address">Address</label>
				</dt>
				<dd>
					<textarea name="address" rows="4"></textarea>
				</dd>
				<dt>
					<span class="reqd help" onclick="$(this).next('span').dialog($.extend($.swag.Main.DIALOG_DEFAULT, {title:'Required'}))">*</span>
					<span style="display:none">Your time zone is required to calculate the correct time differences of yours and your enemies action.</span>
					<label for="timezone">Time zone</label>
				</dt>
				<dd>
					<select name="timezone">
						<option>-12</option>
						<option>-11</option>
						<option>-10</option>
						<option>-09</option>
						<option>-08</option>
						<option>-07</option>
						<option>-06</option>
						<option>-05</option>
						<option>-04</option>
						<option>-03</option>
						<option>-02</option>
						<option>-01</option>
						<option>GMT -00</option>
						<option selected="selected">+01</option>
						<option>+02</option>
						<option>+03</option>
						<option>+04</option>
						<option>+05</option>
						<option>+06</option>
						<option>+07</option>
						<option>+08</option>
						<option>+09</option>
						<option>+10</option>
						<option>+11</option>
						<option>+12</option>
					</select>
				</dd>
				<dt></dt>
				<dd class="submit">
					<button class="submit" onclick="return false;">Register</button>
				</dd>
			</dl>
		</form>
		<a href="#login">abort</a>
	</div>
	<div class="login-form">
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
					<div class="formhelp">No password? <a href="#register">Go here, register!</a><br />
					Forgot password? <a href="#renew-password">Go here, request it!</a></div>
				</dd>
				<dt class="error">
				</dt>
				<dd class="error">
					Error: Login did not work!<br />
					<small>Please check your username and password.</small>
				</dd>
				<dt></dt>
				<dd class="submit">
					<button class="submit" onclick="return false;">Log in</button>
				</dd>
			</dl>
		</form>
	</div>
</div>