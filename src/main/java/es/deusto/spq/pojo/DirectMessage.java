package es.deusto.spq.pojo;

public class DirectMessage {

    private UserData userData;
    private AdminData adminData;
    private MessageData messageData;

    public DirectMessage() {
        // required by serialization
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public UserData getUserData() {
        return this.userData;
    }

    public void setMessageData(MessageData messageData) {
        this.messageData = messageData;
    }

    public MessageData getMessageData() {
        return this.messageData;
    }

	public void setAdminData(AdminData adminData) {
		this.adminData = adminData;
		
	}
    public AdminData getAdminData() {
        return this.adminData;
    }

}