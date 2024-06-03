export const mapStateToProps = (): {
    formatDate: (date: Date) => string
} => {
    const formatDate = (date: Date): string => {
        if (!date) return '';
        const parsedDate = new Date(date);
        const year = parsedDate.getFullYear();
        const month = String(parsedDate.getMonth() + 1).padStart(2, '0');
        const day = String(parsedDate.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
    };

    return {
        formatDate
    };
};