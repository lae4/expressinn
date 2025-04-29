import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getExpressShelfOne = (params) => {
    return getRequest('/expressShelf/getOne', params)
}
export const getExpressShelfList = (params) => {
    return getRequest('/expressShelf/getByPage', params)
}
export const getExpressShelfCount = (params) => {
    return getRequest('/expressShelf/count', params)
}
export const addExpressShelf = (params) => {
    return postRequest('/expressShelf/insert', params)
}
export const editExpressShelf = (params) => {
    return postRequest('/expressShelf/update', params)
}
export const addOrEditExpressShelf = (params) => {
    return postRequest('/expressShelf/insertOrUpdate', params)
}
export const deleteExpressShelf = (params) => {
    return postRequest('/expressShelf/delByIds', params)
}
export const getExpressAreaList = (params) => {
    return getRequest('/expressArea/getAll', params)
}