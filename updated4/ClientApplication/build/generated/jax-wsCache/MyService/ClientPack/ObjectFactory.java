
package ClientPack;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ClientPack package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetDevicesByServiceTypeResponse_QNAME = new QName("http://CloudPack/", "GetDevicesByServiceTypeResponse");
    private final static QName _GetDevicesByDeviceNameResponse_QNAME = new QName("http://CloudPack/", "GetDevicesByDeviceNameResponse");
    private final static QName _CheckUIDResponse_QNAME = new QName("http://CloudPack/", "CheckUIDResponse");
    private final static QName _Login_QNAME = new QName("http://CloudPack/", "login");
    private final static QName _GetDevicesByServiceType_QNAME = new QName("http://CloudPack/", "GetDevicesByServiceType");
    private final static QName _GetAllDevicesResponse_QNAME = new QName("http://CloudPack/", "GetAllDevicesResponse");
    private final static QName _GetDevicesByDeviceName_QNAME = new QName("http://CloudPack/", "GetDevicesByDeviceName");
    private final static QName _GetDevicesByDeviceTypeResponse_QNAME = new QName("http://CloudPack/", "GetDevicesByDeviceTypeResponse");
    private final static QName _CheckUID_QNAME = new QName("http://CloudPack/", "CheckUID");
    private final static QName _GetDeviceDetailsResponse_QNAME = new QName("http://CloudPack/", "getDeviceDetailsResponse");
    private final static QName _GetDeviceDetails_QNAME = new QName("http://CloudPack/", "getDeviceDetails");
    private final static QName _SignUp_QNAME = new QName("http://CloudPack/", "SignUp");
    private final static QName _SignUpResponse_QNAME = new QName("http://CloudPack/", "SignUpResponse");
    private final static QName _StreamVideoResponse_QNAME = new QName("http://CloudPack/", "StreamVideoResponse");
    private final static QName _GetDevicesByDeviceType_QNAME = new QName("http://CloudPack/", "GetDevicesByDeviceType");
    private final static QName _LoginResponse_QNAME = new QName("http://CloudPack/", "loginResponse");
    private final static QName _StreamVideo_QNAME = new QName("http://CloudPack/", "StreamVideo");
    private final static QName _GetAllDevices_QNAME = new QName("http://CloudPack/", "GetAllDevices");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ClientPack
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SignUpResponse }
     * 
     */
    public SignUpResponse createSignUpResponse() {
        return new SignUpResponse();
    }

    /**
     * Create an instance of {@link StreamVideoResponse }
     * 
     */
    public StreamVideoResponse createStreamVideoResponse() {
        return new StreamVideoResponse();
    }

    /**
     * Create an instance of {@link GetDevicesByDeviceType }
     * 
     */
    public GetDevicesByDeviceType createGetDevicesByDeviceType() {
        return new GetDevicesByDeviceType();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link StreamVideo }
     * 
     */
    public StreamVideo createStreamVideo() {
        return new StreamVideo();
    }

    /**
     * Create an instance of {@link GetAllDevices }
     * 
     */
    public GetAllDevices createGetAllDevices() {
        return new GetAllDevices();
    }

    /**
     * Create an instance of {@link GetDeviceDetails }
     * 
     */
    public GetDeviceDetails createGetDeviceDetails() {
        return new GetDeviceDetails();
    }

    /**
     * Create an instance of {@link SignUp }
     * 
     */
    public SignUp createSignUp() {
        return new SignUp();
    }

    /**
     * Create an instance of {@link GetDevicesByDeviceTypeResponse }
     * 
     */
    public GetDevicesByDeviceTypeResponse createGetDevicesByDeviceTypeResponse() {
        return new GetDevicesByDeviceTypeResponse();
    }

    /**
     * Create an instance of {@link CheckUID }
     * 
     */
    public CheckUID createCheckUID() {
        return new CheckUID();
    }

    /**
     * Create an instance of {@link GetDeviceDetailsResponse }
     * 
     */
    public GetDeviceDetailsResponse createGetDeviceDetailsResponse() {
        return new GetDeviceDetailsResponse();
    }

    /**
     * Create an instance of {@link GetDevicesByDeviceNameResponse }
     * 
     */
    public GetDevicesByDeviceNameResponse createGetDevicesByDeviceNameResponse() {
        return new GetDevicesByDeviceNameResponse();
    }

    /**
     * Create an instance of {@link GetDevicesByServiceTypeResponse }
     * 
     */
    public GetDevicesByServiceTypeResponse createGetDevicesByServiceTypeResponse() {
        return new GetDevicesByServiceTypeResponse();
    }

    /**
     * Create an instance of {@link CheckUIDResponse }
     * 
     */
    public CheckUIDResponse createCheckUIDResponse() {
        return new CheckUIDResponse();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link GetDevicesByServiceType }
     * 
     */
    public GetDevicesByServiceType createGetDevicesByServiceType() {
        return new GetDevicesByServiceType();
    }

    /**
     * Create an instance of {@link GetAllDevicesResponse }
     * 
     */
    public GetAllDevicesResponse createGetAllDevicesResponse() {
        return new GetAllDevicesResponse();
    }

    /**
     * Create an instance of {@link GetDevicesByDeviceName }
     * 
     */
    public GetDevicesByDeviceName createGetDevicesByDeviceName() {
        return new GetDevicesByDeviceName();
    }

    /**
     * Create an instance of {@link DeviceDetails }
     * 
     */
    public DeviceDetails createDeviceDetails() {
        return new DeviceDetails();
    }

    /**
     * Create an instance of {@link MyImage }
     * 
     */
    public MyImage createMyImage() {
        return new MyImage();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDevicesByServiceTypeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CloudPack/", name = "GetDevicesByServiceTypeResponse")
    public JAXBElement<GetDevicesByServiceTypeResponse> createGetDevicesByServiceTypeResponse(GetDevicesByServiceTypeResponse value) {
        return new JAXBElement<GetDevicesByServiceTypeResponse>(_GetDevicesByServiceTypeResponse_QNAME, GetDevicesByServiceTypeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDevicesByDeviceNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CloudPack/", name = "GetDevicesByDeviceNameResponse")
    public JAXBElement<GetDevicesByDeviceNameResponse> createGetDevicesByDeviceNameResponse(GetDevicesByDeviceNameResponse value) {
        return new JAXBElement<GetDevicesByDeviceNameResponse>(_GetDevicesByDeviceNameResponse_QNAME, GetDevicesByDeviceNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckUIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CloudPack/", name = "CheckUIDResponse")
    public JAXBElement<CheckUIDResponse> createCheckUIDResponse(CheckUIDResponse value) {
        return new JAXBElement<CheckUIDResponse>(_CheckUIDResponse_QNAME, CheckUIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CloudPack/", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDevicesByServiceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CloudPack/", name = "GetDevicesByServiceType")
    public JAXBElement<GetDevicesByServiceType> createGetDevicesByServiceType(GetDevicesByServiceType value) {
        return new JAXBElement<GetDevicesByServiceType>(_GetDevicesByServiceType_QNAME, GetDevicesByServiceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllDevicesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CloudPack/", name = "GetAllDevicesResponse")
    public JAXBElement<GetAllDevicesResponse> createGetAllDevicesResponse(GetAllDevicesResponse value) {
        return new JAXBElement<GetAllDevicesResponse>(_GetAllDevicesResponse_QNAME, GetAllDevicesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDevicesByDeviceName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CloudPack/", name = "GetDevicesByDeviceName")
    public JAXBElement<GetDevicesByDeviceName> createGetDevicesByDeviceName(GetDevicesByDeviceName value) {
        return new JAXBElement<GetDevicesByDeviceName>(_GetDevicesByDeviceName_QNAME, GetDevicesByDeviceName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDevicesByDeviceTypeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CloudPack/", name = "GetDevicesByDeviceTypeResponse")
    public JAXBElement<GetDevicesByDeviceTypeResponse> createGetDevicesByDeviceTypeResponse(GetDevicesByDeviceTypeResponse value) {
        return new JAXBElement<GetDevicesByDeviceTypeResponse>(_GetDevicesByDeviceTypeResponse_QNAME, GetDevicesByDeviceTypeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckUID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CloudPack/", name = "CheckUID")
    public JAXBElement<CheckUID> createCheckUID(CheckUID value) {
        return new JAXBElement<CheckUID>(_CheckUID_QNAME, CheckUID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDeviceDetailsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CloudPack/", name = "getDeviceDetailsResponse")
    public JAXBElement<GetDeviceDetailsResponse> createGetDeviceDetailsResponse(GetDeviceDetailsResponse value) {
        return new JAXBElement<GetDeviceDetailsResponse>(_GetDeviceDetailsResponse_QNAME, GetDeviceDetailsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDeviceDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CloudPack/", name = "getDeviceDetails")
    public JAXBElement<GetDeviceDetails> createGetDeviceDetails(GetDeviceDetails value) {
        return new JAXBElement<GetDeviceDetails>(_GetDeviceDetails_QNAME, GetDeviceDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignUp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CloudPack/", name = "SignUp")
    public JAXBElement<SignUp> createSignUp(SignUp value) {
        return new JAXBElement<SignUp>(_SignUp_QNAME, SignUp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignUpResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CloudPack/", name = "SignUpResponse")
    public JAXBElement<SignUpResponse> createSignUpResponse(SignUpResponse value) {
        return new JAXBElement<SignUpResponse>(_SignUpResponse_QNAME, SignUpResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StreamVideoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CloudPack/", name = "StreamVideoResponse")
    public JAXBElement<StreamVideoResponse> createStreamVideoResponse(StreamVideoResponse value) {
        return new JAXBElement<StreamVideoResponse>(_StreamVideoResponse_QNAME, StreamVideoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDevicesByDeviceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CloudPack/", name = "GetDevicesByDeviceType")
    public JAXBElement<GetDevicesByDeviceType> createGetDevicesByDeviceType(GetDevicesByDeviceType value) {
        return new JAXBElement<GetDevicesByDeviceType>(_GetDevicesByDeviceType_QNAME, GetDevicesByDeviceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CloudPack/", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StreamVideo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CloudPack/", name = "StreamVideo")
    public JAXBElement<StreamVideo> createStreamVideo(StreamVideo value) {
        return new JAXBElement<StreamVideo>(_StreamVideo_QNAME, StreamVideo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllDevices }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CloudPack/", name = "GetAllDevices")
    public JAXBElement<GetAllDevices> createGetAllDevices(GetAllDevices value) {
        return new JAXBElement<GetAllDevices>(_GetAllDevices_QNAME, GetAllDevices.class, null, value);
    }

}
