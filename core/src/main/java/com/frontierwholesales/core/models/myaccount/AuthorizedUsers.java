package com.frontierwholesales.core.models.myaccount;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Model (adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy=DefaultInjectionStrategy.OPTIONAL)
public class AuthorizedUsers {

	private Logger log = LoggerFactory.getLogger(AuthorizedUsers.class);
	
	@SlingObject
	SlingHttpServletResponse response;

	@Inject
	SlingHttpServletRequest request;
	
	//Authorized Users, Pending Invites Labels
	@Inject
	@Via("resource")
	@Default(values = "Pending Invites")
	public String pendingInvitesTableHeader;

	@Inject
	@Via("resource")
	@Default(values = "Invite New Users")
	public String pendingInvitesTopButtonText;
	
	@Inject
	@Via("resource")
	@Default(values = "First")
	public String pendingInvitesFirstNameColumnHeader;
	
	@Inject
	@Via("resource")
	@Default(values = "Last")
	public String pendingInvitesLastNameColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Email")
	public String pendingInvitesEmailColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Status")
	public String pendingInvitesStatusColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Actions")
	public String pendingInvitesActionsColumnHeader;
	
	//My Club Members Labels
	@Inject
	@Via("resource")
	@Default(values = "My Club Members")
	public String myClubMembersTableHeader;

	@Inject
	@Via("resource")
	@Default(values = "First")
	public String myClubMembersFirstNameColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Last")
	public String myClubMembersLastNameColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Email")
	public String myClubMembersEmailColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "User Role")
	public String myClubMembersUserRoleColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Status")
	public String myClubMembersStatusColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Deactive / Edit")
	public String myClubMembersActionsColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Edit")
	public String myClubMembersEditButtonText;

	//Invite New User Labels
	@Inject
	@Via("resource")
	@Default(values = "Invite New Users")
	public String inviteNewUsersFormHeader;

	@Inject
	@Via("resource")
	@Default(values = "Name")
	public String inviteNewUsersNameFieldLabel;

	@Inject
	@Via("resource")
	@Default(values = "Name")
	public String inviteNewUsersNameFieldHint;

	@Inject
	@Via("resource")
	@Default(values = "Email")
	public String inviteNewUsersEmailLabel;

	@Inject
	@Via("resource")
	@Default(values = "Email")
	public String inviteNewUsersEmailFieldHint;

	@Inject
	@Via("resource")
	@Default(values = "Invite")
	public String inviteNewUsersButtonText;
	
	@PostConstruct
	protected void activate() {
		
	}

}
