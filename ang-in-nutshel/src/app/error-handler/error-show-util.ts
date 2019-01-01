import swal, { SweetAlertType } from 'sweetalert2';

export class ErrorShowUtil {
    public static popupError(title: string, text: string, confirmButtonText: string, type : SweetAlertType): Promise<any> {
        return swal({
            title: title,
            text: text,
            confirmButtonText: confirmButtonText,
            type: type
        });
    }
}