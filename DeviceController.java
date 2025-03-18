@Controller
public class DeviceController {
    
    @Autowired
    private DeviceService deviceService; // Gọi service để lấy danh sách thiết bị

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        List<Device> devices = deviceService.getAllDevices(); // Lấy danh sách thiết bị từ service
        model.addAttribute("devices", devices); // Truyền danh sách thiết bị sang JSP
        return "dashboard"; // Điều hướng đến trang dashboard.jsp
    }
}
