@Service
public class DeviceService {
    
    private List<Device> deviceList = new ArrayList<>();

    public DeviceService() {
        // Dữ liệu mẫu
        deviceList.add(new Device(1, "Thiết bị 1", 100, true));
        deviceList.add(new Device(2, "Thiết bị 2", 150, false));
        deviceList.add(new Device(3, "Thiết bị 3", 200, true));
    }

    public List<Device> getAllDevices() {
        return deviceList;
    }
}
