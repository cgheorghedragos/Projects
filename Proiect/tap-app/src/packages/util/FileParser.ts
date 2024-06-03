export const base64ToFile = (base64: string, filename: string, mimeType: string): Promise<File> => {
    return fetch(base64)
        .then((res) => res.arrayBuffer())
        .then((buffer) => new File([buffer], filename, { type: mimeType }));
};

export const fileToBase64 = (file: File): Promise<string> => {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => {
            if (reader.result) {
                let encoded = reader.result.toString().replace(/^data:(.*,)?/, '');
                if ((encoded.length % 4) > 0) {
                    encoded += '='.repeat(4 - (encoded.length % 4));
                }
                resolve(encoded as string);
            } else {
                reject(new Error("Failed to convert file to base64"));
            }
        };
        reader.onerror = (error) => reject(error);
    });
};

export function base64ToFileSync(base64String: string, fileName: string): File {
    // Decode the base64 string

    const binaryString = atob(base64String);

    // Create an array of 8-bit unsigned integers
    const byteNumbers = new Array(binaryString.length);
    for (let i = 0; i < binaryString.length; i++) {
        byteNumbers[i] = binaryString.charCodeAt(i);
    }

    const byteArray = new Uint8Array(byteNumbers);

    // Create a blob from the byteArray
    const blob = new Blob([byteArray], { type: 'application/octet-stream' });

    // Create a file from the blob
    return new File([blob], fileName);
}