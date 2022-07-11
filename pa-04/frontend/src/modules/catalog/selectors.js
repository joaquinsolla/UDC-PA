const getModuleState = state => state.catalog;

export const getCategories = state =>
    getModuleState(state).categories;

export const getCategoryName = (categories, id) => {

    if (!categories) {
        return '';
    }

    const category = categories.find(category => category.id === id);

    if (!category) {
        return '';
    }

    return category.name;

}

export const getProductSearch = state =>
    getModuleState(state).productSearch;

export const getProduct = state =>
    getModuleState(state).product;